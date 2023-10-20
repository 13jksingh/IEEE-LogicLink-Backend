FROM openjdk:latest
VOLUME /tmp
EXPOSE 8080

LABEL maintainer="IEEE ieee@pec.edu.in"
LABEL version="1.0"
LABEL description="Backend API for the ieee hackathon"

ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]