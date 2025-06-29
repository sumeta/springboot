FROM adoptopenjdk/openjdk11-openj9:alpine-jre

VOLUME /tmp

RUN mkdir /app

EXPOSE 8080

ADD build/libs/webclient-0.0.1-SNAPSHOT.jar /app.jar

COPY .k8s/Bangkok /etc/localtime

RUN echo Asia/Bangkok > /etc/timezone

ENTRYPOINT exec java $JAVA_OPTS $TIME_ZONE -jar /app.jar
