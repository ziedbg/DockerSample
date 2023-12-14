package fr.ileadconsulting.condinggame;

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

public class DockerService {

    private DockerClient dockerClient;

    public DockerService(String dockerHost) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .build();
        this.dockerClient = DockerClientBuilder.getInstance(config).build();
    }

    public void executeJavaScript(String containerId, String script) {
        try {
            ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                    .withCmd("node", "-e", script)
                    .withAttachStdout(true)
                    .withAttachStderr(true)
                    .exec();

            dockerClient.execStartCmd(execCreateCmdResponse.getId())
                    .exec(new ExecStartResultCallback(System.out, System.err))
                    .awaitCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runJavaScriptTests(String containerId, String testFilePath, String configPath) {
        try {
            ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                    .withCmd("jest", "--config", configPath, testFilePath)
                    .withAttachStdout(true)
                    .withAttachStderr(true)
                    .exec();

            dockerClient.execStartCmd(execCreateCmdResponse.getId())
                    .exec(new ExecStartResultCallback(System.out, System.err))
                    .awaitCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFileToContainer(String containerId, String resourcePath, String containerPath) throws Exception {
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

    public void close() {
        try {
            dockerClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
