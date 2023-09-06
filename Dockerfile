FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ADD /target/movie-ticket-booking.jar movie-ticket-booking.jar
ENTRYPOINT ["java", "-jar", "/movie-ticket-booking.jar"]