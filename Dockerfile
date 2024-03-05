FROM maven:3.8.3-openjdk-17 AS build
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY pom.xml $APP_HOME
COPY src $APP_HOME/src/
RUN mvn package -DskipTests

FROM openjdk:17-oracle
ENV APP_HOME=/app/
ENV ARTIFACT_NAME=krainet-test-task-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=$APP_HOME/target/$ARTIFACT_NAME
COPY --from=build $JAR_FILE /opt/krainet-test-task/app.jar
ENTRYPOINT ["java", "-jar", "/opt/krainet-test-task/app.jar"]