FROM node:alpine

# Affiche un message pour indiquer l'installation de Jest
RUN echo "Installing Jest..." && \
    npm install -g jest

# Affiche un message pour indiquer la création d'un utilisateur non-root
RUN echo "Creating a non-root user 'appuser'..." && \
    adduser -D appuser

# Affiche un message pour indiquer la création des répertoires de projet
RUN echo "Creating project directories..." && \
    mkdir -p /app/incrementTest && \
    mkdir -p /app/reverseWordsTest

# Affiche un message pour indiquer la copie des fichiers de test
RUN echo "Copying test files..."
COPY ./incrementTest/incrementIfPositive.test.js /app/incrementTest/incrementIfPositive.test.js
COPY ./reverseWordsTest/reverseWords.test.js /app/reverseWordsTest/reverseWords.test.js

# Affiche un message pour indiquer la copie des fichiers de configuration Jest
RUN echo "Copying Jest configuration files..."
COPY ./incrementTest/jest.config.js /app/incrementTest/jest.config.js
COPY ./reverseWordsTest/jest.config.js /app/reverseWordsTest/jest.config.js

# Affiche un message pour indiquer le changement de propriété des répertoires et fichiers
RUN echo "Changing ownership of directories and files to 'appuser'..." && \
    chown -R appuser:appuser /app

# Change l'utilisateur courant vers 'appuser'
USER appuser

WORKDIR /app

CMD ["tail", "-f", "/dev/null"]
