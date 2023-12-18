# DockerSample: Exécution de Scripts JavaScript dans Docker via Java

Ce projet illustre l'exécution de scripts JavaScript dans des conteneurs Docker gérés par une application Java. Il démontre l'utilisation de design patterns dans le code Java, l'orchestration des conteneurs avec Docker Compose et l'architecture DooD (Docker outside of Docker).

## Table des Matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration du Projet Java](#configuration-du-projet-java)
- [Utilisation de Docker Compose](#utilisation-de-docker-compose)
- [Vérification](#vérification)
- [Architecture](#architecture)
- [Auteur](#auteur)
- [Licence](#licence)

## Prérequis

- Docker et Docker Compose
- Java (JDK 17 ou supérieur)
- Maven

## Installation

### Cloner le Répertoire

```bash
git clone https://github.com/ziedbg/DockerSample
cd DockerSample
```

## Configuration du Projet Java

### Installer les Dépendances
```bash
mvn clean install
```

### Utilisation de Docker Compose

Après avoir construit le projet avec Maven, lancez Docker Compose pour démarrer l'application et les conteneurs Docker associés :

```bash
docker-compose up --build
```
## Exécution

Une fois Docker Compose lancé, l'application Java (DockerManager) est exécutée automatiquement dans son propre conteneur. Elle gère la création et la supervision des autres conteneurs Docker nécessaires pour l'exécution des scripts JavaScript.
## Vérification

Pour vérifier l'exécution du script JavaScript et des tests unitaires :

- Consultez les logs de l'application Java.
- Utilisez docker logs sur les conteneurs pertinents.
## Architecture
Le projet adopte une architecture DooD, permettant au conteneur Java de communiquer avec le Docker host et de gérer d'autres conteneurs. Le fichier docker-compose.yml orchestre la création et la gestion de tous les conteneurs nécessaires.

## Auteur

Ce projet et l'ensemble de son code sont la propriété exclusive de I-Lead-Consulting. Tous les droits sont réservés. Aucune partie de ce projet ne peut être copiée, modifiée, publiée, transmise ou vendue sans l'autorisation écrite préalable de I-Lead-Consulting.

## Restrictions de Licence

Ce projet est une propriété privée et n'est pas sous licence open-source. Toute utilisation, duplication, modification ou distribution du code ou des parties de ce projet est strictement interdite, sauf accord explicite de I-Lead-Consulting.


