FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
ARG JAR_FILE=target/helpdesk-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} helpdesk.jar
ENTRYPOINT ["java", "-jar", "helpdesk.jar"]
