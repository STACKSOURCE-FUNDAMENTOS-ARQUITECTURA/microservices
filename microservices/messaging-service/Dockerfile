FROM openjdk:17-alpine
COPY ./target/messaging-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch messaging-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/messaging-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /messaging-service-0.0.1-SNAPSHOT.jar