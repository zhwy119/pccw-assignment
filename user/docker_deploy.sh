#!/bin/bash
set -e
mvn clean package docker:build

docker_project=pccw-assignment/user
docker_tag=latest
docker_image=${docker_project}:latest
docker_container_name=pccw-assignment-user-docker


docker run --name ${docker_container_name} -p 9999:9999 -d ${docker_image}

exit
eeooff
