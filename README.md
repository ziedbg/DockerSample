# DockerSample: Exécution de Scripts JavaScript dans Docker via Java

Ce projet démontre comment exécuter des scripts JavaScript dans un conteneur Docker en utilisant une application Java. L'application Java envoie des commandes au conteneur Docker pour exécuter des scripts JavaScript.

## Table des Matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration du Projet Java](#configuration-du-projet-java)
- [Exécution](#exécution)
- [Vérification](#vérification)
- [Auteur](#auteur)
- [Licence](#licence)

## Prérequis

- Docker
- Java (JDK 8 ou supérieur)
- Maven

## Installation

### Cloner le Répertoire

git clone https://github.com/ziedbg/DockerSample
cd DockerSample


### Construire l' Image Docker alpine-nodejs pour l'exemple DockerInteraction

cd resources/dockers/alpine-nodejs
docker build -t nodej-alpine .


### Lancer le Conteneur Docker  pour l'exemple DockerInteraction


docker run -d --name c-nodejs-alpine nodej-alpine



### Construire l' Image Docker nodejs-test-runner pour l'exemple DockerTestRunner

cd resources/nodejs-tests
docker build -t nodejs-test-runner .



### Lancer le Conteneur Docker nodejs-test-runner pour l'exemple DockerTestRunner

docker run -d --name js-test-container nodejs-test-runner




## Configuration du Projet Java

### Installer les Dépendances

mvn clean install


## Exécution

Pour tester les fonctionnalités de ce projet, suivez les étapes ci-dessous selon l'exemple que vous souhaitez exécuter :

### Exemple avec DockerInteraction

- **Objectif :** Ce scénario illustre l'exécution d'une fonction JavaScript spécifique sur le conteneur Docker nommé `c-nodejs-alpine`.
- **Démarrage :** Lancez la classe `DockerInteraction` depuis votre environnement de développement Java ou en utilisant une ligne de commande Java. Cette classe envoie une commande au conteneur Docker pour exécuter la fonction JavaScript.

### Exemple avec DockerTestRunner

- **Objectif :** Dans cet exemple, deux fonctions JavaScript sont copiées dans des projets distincts au sein du conteneur Docker `js-test-container`. Des tests unitaires sont ensuite exécutés pour chaque fonction, et les résultats des tests sont récupérés et affichés dans la console Java.
- **Démarrage :** Exécutez la classe `DockerTestRunner`. Cette classe gère la copie des fichiers de script et de test dans le conteneur, l'exécution des tests, et l'affichage des résultats.

Assurez-vous de suivre les instructions de configuration et d'installation avant d'exécuter ces exemples pour garantir leur bon fonctionnement.

## Vérification

Pour vérifier l'exécution du script JavaScript :

- Consultez la console de l'application Java.
- Utilisez `docker logs c-nodejs-alpine`.
- Utilisez `docker logs js-test-container`.

## Auteur

Mohamed Zied BEN GHORBAL

## Licence

Ce projet est sous licence MIT.

