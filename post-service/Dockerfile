FROM openjdk:22-jdk
WORKDIR /post-service
COPY build/libs/*.jar /post-service/app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/post-service/app.jar"]
