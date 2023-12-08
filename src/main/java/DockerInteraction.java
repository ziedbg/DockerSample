import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;

import java.io.IOException;

public class DockerInteraction {
    public static void main(String[] args) {

        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375") // Remplacez avec votre URL de Docker
                .build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        // Commande pour exécuter un script JavaScript dans le conteneur
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd("c-nodejs-alpine")
                .withCmd("node", "-e", "console.log('Hello from JavaScript')")
                .withAttachStdout(true)
                .withAttachStderr(true)
                .exec();

        // Exécute la commande et attend que cela se termine
        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId())
                    .exec(new ExecStartResultCallback(System.out, System.err))
                    .awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            dockerClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
