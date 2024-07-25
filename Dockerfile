FROM openjdk:17-jdk-slim
ADD target/questions_history-2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]