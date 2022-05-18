FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/customer-service-app
COPY ${JAR_FILE} /opt/customer-service-app/customer-service.jar
ENTRYPOINT ["java","-jar","/opt/customer-service-app/customer-service.jar"]