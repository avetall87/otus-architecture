FROM bellsoft/liberica-openjdk-alpine:14
ARG APPLICATION_FROM

COPY ./build/libs/$APPLICATION_FROM.jar /usr/app/app.jar
COPY ./config/application.properties /usr/app/application.properties
WORKDIR /usr/app
EXPOSE 8083
CMD java -jar app.jar --spring.config.location=file:/usr/app/application.properties
