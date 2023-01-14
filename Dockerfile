FROM openjdk:17
EXPOSE 8080
ADD target/booking.jar booking.jar
ENTRYPOINT ["java","-jar","booking.jar"]