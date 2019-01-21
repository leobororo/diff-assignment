FROM openjdk:8-jre-alpine

WORKDIR /usr/src

COPY build/libs/diff-assignment-1.0.jar .

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar diff-assignment-1.0.jar
