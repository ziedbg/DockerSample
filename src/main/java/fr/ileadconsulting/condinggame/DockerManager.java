package fr.ileadconsulting.condinggame;

public class DockerManager {
    public static void main(String[] args) {
        DockerService dockerService = new DockerService("tcp://localhost:2375");

        // Exemple d'exécution de script JavaScript
        dockerService.executeJavaScript("coding-game-js-execution", "console.log('Hello from JavaScript')");

        // Exemple d'exécution de tests JavaScript
        dockerService.runJavaScriptTests("coding-game-js-test-runner", "/app/incrementTest/incrementIfPositive.test.js", "/app/incrementTest/jest.config.js");

        // 2eme Exemple d'exécution de tests JavaScript
        dockerService.runJavaScriptTests("coding-game-js-test-runner", "/app/reverseWordsTest/reverseWords.test.js", "/app/reverseWordsTest/jest.config.js");

        dockerService.close();
    }
}
