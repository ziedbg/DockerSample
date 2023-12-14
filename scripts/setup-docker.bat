@echo off
:: Setup-docker.bat - Script pour automatiser la construction et le lancement des conteneurs Docker pour Coding Game

:: Définition des variables
set JS_ENV_IMAGE_NAME=i-lead-consulting/coding-game-js-env
set JS_TEST_ENV_IMAGE_NAME=i-lead-consulting/coding-game-js-test-env
set JS_ENV_CONTAINER_NAME=coding-game-js-execution
set JS_TEST_ENV_CONTAINER_NAME=coding-game-js-test-runner
set JS_ENV_DOCKERFILE_PATH=..\src\main\resources\dockers\alpine-nodejs
set JS_TEST_ENV_DOCKERFILE_PATH=..\src\main\resources\dockers\nodejs-tests

echo Building Docker Images and Running Containers...

:: Arrêter et supprimer les conteneurs existants s'ils existent
echo Checking and stopping existing Docker containers...
docker stop %JS_ENV_CONTAINER_NAME% 2>nul
docker rm %JS_ENV_CONTAINER_NAME% 2>nul
docker stop %JS_TEST_ENV_CONTAINER_NAME% 2>nul
docker rm %JS_TEST_ENV_CONTAINER_NAME% 2>nul

:: Construire l'image Docker pour l'environnement d'exécution JavaScript
echo Building JavaScript Execution Environment Image...
cd %JS_ENV_DOCKERFILE_PATH%
if not exist Dockerfile (
    echo Dockerfile not found in %JS_ENV_DOCKERFILE_PATH%
    exit /b 1
)
docker build -t %JS_ENV_IMAGE_NAME% .
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
cd ..\..\..\..\..\scripts

:: Construire l'image Docker pour l'environnement de test JavaScript
echo Building JavaScript Test Environment Image...
cd %JS_TEST_ENV_DOCKERFILE_PATH%
if not exist Dockerfile (
    echo Dockerfile not found in %JS_TEST_ENV_DOCKERFILE_PATH%
    exit /b 1
)
docker build -t %JS_TEST_ENV_IMAGE_NAME% .
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
cd ..\..\..\..\..\scripts

:: Lancer le conteneur Docker pour l'environnement d'exécution JavaScript
echo Running JavaScript Execution Environment Container...
docker run -d --name %JS_ENV_CONTAINER_NAME% %JS_ENV_IMAGE_NAME%
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

:: Lancer le conteneur Docker pour l'environnement de test JavaScript
echo Running JavaScript Test Environment Container...
docker run -d --name %JS_TEST_ENV_CONTAINER_NAME% %JS_TEST_ENV_IMAGE_NAME%
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo Setup Complete!
