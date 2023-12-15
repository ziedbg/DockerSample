package fr.ileadconsulting.codinggame.service;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class DockerClientSingleton {

    private static DockerClient instance;

    private DockerClientSingleton() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static synchronized DockerClient getInstance(String dockerHost) {
        if (instance == null) {
            DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost(dockerHost)
                    .build();
            instance = DockerClientBuilder.getInstance(config).build();
        }
        return instance;
    }
}

