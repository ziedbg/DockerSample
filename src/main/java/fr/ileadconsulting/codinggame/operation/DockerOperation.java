
package fr.ileadconsulting.codinggame.operation;

import com.github.dockerjava.api.DockerClient;

public interface DockerOperation {
    void execute(DockerClient dockerClient, String containerId) throws Exception;
}
