FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/RestApi-0.0.1-SNAPSHOT.jar userapi.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/userapi.jar"]
