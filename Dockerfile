FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/dynaccurateTest-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]