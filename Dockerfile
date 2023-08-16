FROM maven:3.8.5-openjdk-17 as build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn package

FROM openjdk:17
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/java-health-risks-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]