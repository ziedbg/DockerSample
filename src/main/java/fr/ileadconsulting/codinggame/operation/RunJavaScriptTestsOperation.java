package fr.ileadconsulting.codinggame.operation;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class RunJavaScriptTestsOperation implements DockerOperation {
    private final String sourceFilePath, containerPath, testFilePath, configPath;

    public RunJavaScriptTestsOperation(String sourceFilePath, String containerPath, String testFilePath, String configPath) {
        this.sourceFilePath = sourceFilePath;
        this.containerPath = containerPath;
        this.testFilePath = testFilePath;
        this.configPath = configPath;
    }

    @Override
    public void execute(DockerClient dockerClient, String containerId) throws Exception {
        copyFileToContainer(dockerClient, containerId, sourceFilePath, containerPath);

        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withCmd("jest", "--config", containerPath + configPath, containerPath + testFilePath)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .exec();

        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ExecStartResultCallback(System.out, System.err))
                .awaitCompletion();
    }

    private void copyFileToContainer(DockerClient dockerClient, String containerId, String resourcePath, String containerPath) throws Exception {
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
}
