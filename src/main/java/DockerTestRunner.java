import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class DockerTestRunner {

    private DockerClient dockerClient;
    private String containerId;

    public DockerTestRunner(String containerId) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .build();

        this.dockerClient = DockerClientBuilder.getInstance(config).build();
        this.containerId = containerId;
    }

    public void runTests() {
        try {
            // Chemins des fichiers de configuration Jest pour chaque test
            String incrementTestConfigPath = "/app/incrementTest/jest.config.js";
            String reverseWordsTestConfigPath = "/app/reverseWordsTest/jest.config.js";

            // Copier et exécuter les tests pour incrementIfPositive
            copyFileToContainer("nodejs-tests/incrementTest/incrementIfPositive.js", "/app/incrementTest/");
            executeTest("/app/incrementTest/incrementIfPositive.test.js", incrementTestConfigPath);

            // Copier et exécuter les tests pour reverseWords
            copyFileToContainer("nodejs-tests/reverseWordsTest/reverseWords.js", "/app/reverseWordsTest/");
            executeTest("/app/reverseWordsTest/reverseWords.test.js", reverseWordsTestConfigPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyFileToContainer(String resourcePath, String containerPath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Ressource " + resourcePath + " introuvable");
            }

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 TarArchiveOutputStream taos = new TarArchiveOutputStream(baos)) {
                String entryName = new File(resourcePath).getName();
                TarArchiveEntry tarEntry = new TarArchiveEntry(entryName);
                tarEntry.setSize(inputStream.available());
                taos.putArchiveEntry(tarEntry);
                IOUtils.copy(inputStream, taos);
                taos.closeArchiveEntry();
                taos.finish();

                try (InputStream tarInputStream = new ByteArrayInputStream(baos.toByteArray())) {
                    dockerClient.copyArchiveToContainerCmd(containerId)
                            .withTarInputStream(tarInputStream)
                            .withRemotePath(containerPath)
                            .exec();
                }
            }
        }
    }


    private void executeTest(String testFilePath, String configPath) throws Exception {
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withCmd("jest", "--config", configPath, testFilePath)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .exec();

        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ExecStartResultCallback(System.out, System.err))
                .awaitCompletion();
    }

    public static void main(String[] args) {
        DockerTestRunner testRunner = new DockerTestRunner("js-test-container");
        testRunner.runTests();
    }
}
