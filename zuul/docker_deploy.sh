#!/bin/bash
set -e
mvn clean package docker:build

docker_project=pccw-assignment-zuul
docker_tag=latest
docker_image=${docker_project}:latest
docker_container_name=${docker_project}


docker run --name ${docker_container_name} --link pccw-assignment-eureka-server:pccw-assignment-eureka-server --link pccw-assignment-user:pccw-assignment-user -p 9095:9095 -d ${docker_image}

exit
eeooff
