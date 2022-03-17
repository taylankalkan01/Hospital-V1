#base docker image
FROM openjdk:11
LABEL maintainer="hospital.net"
ADD target/Hospital-0.0.1-SNAPSHOT.jar springboot-hospital-docker.jar
ENTRYPOINT ["java", "-jar", "springboot-hospital-docker.jar"]