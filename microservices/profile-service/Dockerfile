FROM openjdk:17-alpine
COPY ./target/profile-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch profile-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/profile-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /profile-service-0.0.1-SNAPSHOT.jar