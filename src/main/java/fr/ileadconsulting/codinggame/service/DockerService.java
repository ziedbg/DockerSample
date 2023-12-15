package fr.ileadconsulting.codinggame.service;

import com.github.dockerjava.api.DockerClient;
import fr.ileadconsulting.codinggame.operation.DockerOperation;

public class DockerService {

    private DockerClient dockerClient;

    public DockerService(String dockerHost) {
        this.dockerClient = DockerClientSingleton.getInstance(dockerHost);
    }

    public void performOperation(DockerOperation operation, String containerId) {
        try {
            operation.execute(dockerClient, containerId);
        } catch (Exception e) {
            e.printStackTrace();  // Gestion d'erreurs plus sophistiquée peut être ajoutée ici
        }
    }

    public void close() {
        if (dockerClient != null) {
            try {
                dockerClient.close();
            } catch (Exception e) {
                // Gérer l'exception de la fermeture du client Docker
                e.printStackTrace();
            }
        }
    }
}
