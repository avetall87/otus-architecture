FROM bellsoft/liberica-openjdk-alpine:14

ENV SERVER_PORT=8083
ENV SPRINGPROFILES=acturator

COPY ./build/libs/**.jar /usr/app/app.jar
COPY ./config/application.yml /usr/app/application.yml
WORKDIR /usr/app
EXPOSE $SERVER_PORT

CMD java -Djava.security.egd=file:/dev/urandom -Dserver.port=$SERVER_PORT -Dserver.address=0.0.0.0 -jar app.jar --spring.config.location=file:/usr/app/application.yml