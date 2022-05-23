FROM openjdk:17
ARG JAR_FILE=fraud-service/target/fraud-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} fraud-service.jar
EXPOSE 30001
ENTRYPOINT ["java","-jar","fraud-service.jar"]