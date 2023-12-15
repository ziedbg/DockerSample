package fr.ileadconsulting.codinggame.operation;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;

public class ExecuteJavaScriptOperation implements DockerOperation {
    private final String script;

    public ExecuteJavaScriptOperation(String script) {
        this.script = script;
    }

    @Override
    public void execute(DockerClient dockerClient, String containerId) throws Exception {
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withCmd("node", "-e", script)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .exec();

        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ExecStartResultCallback(System.out, System.err))
                .awaitCompletion();
    }
}
