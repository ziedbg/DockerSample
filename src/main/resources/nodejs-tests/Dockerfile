FROM node:alpine

# Installe Jest
RUN npm install -g jest

# Crée un utilisateur non-root appelé 'appuser'
RUN adduser -D appuser

# Crée les répertoires pour les projets de test
RUN mkdir -p /app/incrementTest
RUN mkdir -p /app/reverseWordsTest

# Copie les fichiers de test
COPY ./incrementTest/incrementIfPositive.test.js /app/incrementTest/incrementIfPositive.test.js
COPY ./reverseWordsTest/reverseWords.test.js /app/reverseWordsTest/reverseWords.test.js

COPY ./incrementTest/jest.config.js /app/incrementTest/jest.config.js
COPY ./reverseWordsTest/jest.config.js /app/reverseWordsTest/jest.config.js

# Change la propriété des répertoires et des fichiers vers 'appuser'
RUN chown -R appuser:appuser /app

# Change l'utilisateur courant vers 'appuser'
USER appuser

WORKDIR /app

CMD ["tail", "-f", "/dev/null"]
