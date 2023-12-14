# Utilise Alpine comme image de base
FROM alpine:latest

# Affiche un message pour indiquer l'installation de Node.js
RUN echo "Installing Node.js and npm..." && \
    apk add --update nodejs npm

# Définit le répertoire de travail
WORKDIR /app

# Affiche un message pour indiquer que l'image restera en attente
RUN echo "Setting up the container to stay up with 'tail -f /dev/null'..."

# L'image reste en attente
CMD tail -f /dev/null
