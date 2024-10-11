FROM openjdk:22-jdk
WORKDIR /audit-service
COPY build/libs/*.jar /audit-service/app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/audit-service/app.jar"]
