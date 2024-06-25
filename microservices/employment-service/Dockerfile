FROM openjdk:17-alpine
COPY ./target/employment-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch employment-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/employment-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /employment-service-0.0.1-SNAPSHOT.jar