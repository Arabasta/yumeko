#!/bin/zsh

# build backend
mvn clean install -f ./spring_backend/pom.xml

# enable docker buildx
docker buildx create --use

# build spring backend for linux
docker buildx build --platform linux/arm64 -t spring-backend:latest -f ./spring_backend/Dockerfile ./spring_backend --load
