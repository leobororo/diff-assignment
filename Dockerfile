FROM openjdk:8-jre-alpine

WORKDIR /usr/src

COPY build/libs/diff_assessment-1.0.jar .

ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8093"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar diff_assessment-1.0.jar
