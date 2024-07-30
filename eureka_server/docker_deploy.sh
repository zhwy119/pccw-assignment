#!/bin/bash
set -e
mvn clean package docker:build

docker_project=pccw-assignment-eureka-server
docker_tag=latest
docker_image=${docker_project}:latest
docker_container_name=${docker_project}


docker run --name ${docker_container_name} -p 9094:9094 -d ${docker_image}

exit
eeooff
