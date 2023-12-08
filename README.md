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


### Construire l'Image Docker

cd resources/dockers/alpine-nodejs
docker build -t nodej-alpine .


### Lancer le Conteneur Docker

docker run -d --name c-nodejs-alpine nodej-alpine


## Configuration du Projet Java

### Installer les Dépendances

mvn clean install


## Exécution

Exécutez l'application Java :


## Vérification

Pour vérifier l'exécution du script JavaScript :

- Consultez la console de l'application Java.
- Utilisez `docker logs c-nodejs-alpine`.

## Auteur

Mohamed Zied BEN GHORBAL

## Licence

Ce projet est sous licence MIT.
