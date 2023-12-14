@echo off
echo Building Docker Images and Running Containers...

echo Building Node.js Alpine Image...
cd ..\src\main\resources\dockers\alpine-nodejs
docker build -t nodej-alpine .
cd ..\..\..\..\..\scripts

echo Building Node.js Test Runner Image...
cd ..\src\main\resources\dockers\nodejs-tests
docker build -t nodejs-test-runner .
cd ..\..\..\..\..\scripts

echo Running Node.js Alpine Container...
docker run -d --name c-nodejs-alpine nodej-alpine

echo Running Node.js Test Runner Container...
docker run -d --name js-test-container nodejs-test-runner

echo Setup Complete!
