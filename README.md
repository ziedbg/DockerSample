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


### Construire les Images Docker et Lancer les Conteneurs

Exécutez le fichier batch setup-docker.bat (Windows) ou setup-docker.sh (Linux/Mac) pour construire automatiquement les images Docker et lancer les conteneurs :

./setup-docker.bat  # Sur Windows
./setup-docker.sh   # Sur Linux/Mac



## Configuration du Projet Java

### Installer les Dépendances

mvn clean install


## Exécution

Suivez les instructions ci-dessous pour exécuter les fonctionnalités de ce projet :

- **Objectif :** Ce projet comprend désormais une classe principale `DockerManager` qui démontre l'utilisation de diverses opérations Docker, y compris l'exécution de scripts JavaScript et l'exécution de tests JavaScript dans des conteneurs Docker.
- **Démarrage :** Lancez la classe `DockerManager` depuis votre environnement de développement Java ou en utilisant une ligne de commande Java. Cette classe centralise l'exécution de différentes opérations en interagissant avec les conteneurs Docker.

### Détails des Opérations dans DockerManager

1. **Exécution de Script JavaScript :** 
   - La classe envoie une commande à un conteneur Docker (par exemple, `coding-game-js-execution`) pour exécuter un script JavaScript.
   - Le script est spécifié dans le code de la classe et peut être modifié selon les besoins.

2. **Exécution de Tests JavaScript :** 
   - Deux fonctions JavaScript, avec leurs tests unitaires associés, sont copiées dans des projets distincts au sein d'un conteneur Docker (par exemple, `coding-game-js-test-runner`).
   - Les tests unitaires pour ces fonctions sont exécutés dans le conteneur, et les résultats des tests sont récupérés et affichés dans la console Java.

Assurez-vous de suivre les instructions de configuration et d'installation avant d'exécuter cette classe pour garantir le bon fonctionnement du projet.

## Vérification

Pour vérifier l'exécution du script JavaScript :

- Consultez la console de l'application Java.
- Utilisez docker logs coding-game-js-execution.
- Utilisez docker logs coding-game-js-test-runner.

## Auteur

Mohamed Zied BEN GHORBAL

## Licence

Ce projet est sous licence MIT.

