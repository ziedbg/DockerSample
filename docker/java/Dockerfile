# Utiliser une image de base Alpine avec OpenJDK
FROM openjdk:17-jdk-alpine

# Installer le client Docker
RUN apk add --no-cache docker-cli

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur avec un nom fixe
COPY ./target/docker-image-builder-*-bundle.jar /app/coding-game.jar

# Commande par défaut à exécuter au démarrage du conteneur
CMD ["java", "-jar", "/app/coding-game.jar"]
