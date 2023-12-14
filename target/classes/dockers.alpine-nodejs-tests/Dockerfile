# Utilise Alpine comme image de base
FROM alpine:latest

# Installe Node.js et npm
RUN apk add --update nodejs npm

# Installe Jest pour les tests unitaires
RUN npm install -g jest

# Définit le répertoire de travail
WORKDIR /app

# L'image reste en attente
CMD tail -f /dev/null
