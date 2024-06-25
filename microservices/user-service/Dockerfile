FROM openjdk:17-alpine
COPY ./target/user-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch user-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/user-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /user-service-0.0.1-SNAPSHOT.jar