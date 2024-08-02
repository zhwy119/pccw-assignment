FROM openjdk:8
VOLUME /tmp/tomcat
ADD eureka-server-0.0.1-SNAPSHOT.jar eureka-server-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server-docker.jar", "--spring.profiles.active=test"]
