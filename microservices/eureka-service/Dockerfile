FROM openjdk:17-alpine
COPY ./target/eureka-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch eureka-service-0.0.1-SNAPSHOT.jar'
RUN chmod +x /eureka-service-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka-service-0.0.1-SNAPSHOT.jar"]