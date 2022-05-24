FROM eclipse-temurin:17
ARG JAR_FILE=customer-service/target/customer-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} customer-service.jar
EXPOSE 30000
ENTRYPOINT ["java","-jar","customer-service.jar"]