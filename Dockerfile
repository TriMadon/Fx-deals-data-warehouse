FROM openjdk:22-jdk-slim
VOLUME /tmp
COPY target/Fx-deals-data-warehouse-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
