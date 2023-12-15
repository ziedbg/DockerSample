package fr.ileadconsulting.codinggame;

import fr.ileadconsulting.codinggame.operation.ExecuteJavaScriptOperation;
import fr.ileadconsulting.codinggame.operation.RunJavaScriptTestsOperation;
import fr.ileadconsulting.codinggame.service.DockerService;

public class DockerManager {
    public static void main(String[] args) {
        DockerService dockerService = new DockerService("tcp://localhost:2375");

        try {
            // Exécution d'un script JavaScript
            dockerService.performOperation(new ExecuteJavaScriptOperation("console.log('Hello from JavaScript')"), "coding-game-js-execution");

            // Exécution du test JavaScript incrementIfPositive
            dockerService.performOperation(new RunJavaScriptTestsOperation(
                            "dockers/nodejs-tests/incrementTest/incrementIfPositive.js",
                            "/app/incrementTest/",
                            "incrementIfPositive.test.js",
                            "jest.config.js"),
                    "coding-game-js-test-runner");

            // Exécution du test JavaScript reverseWords.js
            dockerService.performOperation(new RunJavaScriptTestsOperation(
                            "dockers/nodejs-tests/reverseWordsTest/reverseWords.js",
                            "/app/reverseWordsTest/",
                            "reverseWords.test.js",
                            "jest.config.js"),
                    "coding-game-js-test-runner");







        } catch (Exception e) {
            e.printStackTrace();
            // Gestion d'erreurs plus sophistiquée peut être ajoutée ici
        } finally {
            dockerService.close();  // Assurez-vous que le service Docker est fermé correctement
        }
    }
}
