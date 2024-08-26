FROM eclipse-temurin:21.0.3_9-jdk

COPY target/challenge-0.0.1-SNAPSHOT.jar challenge-app.jar

ENTRYPOINT ["java", "-jar", "/app/target/challenge-0.0.1-SNAPSHOT.jar"]
