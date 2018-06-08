FROM maven:latest as builder

ARG REPO='https://github.com/kubernauts/prometheus_example.git'

WORKDIR /prometheus_example

RUN git clone $REPO . && cd springboot/instrumented && mvn package

FROM openjdk:8-jdk-slim

LABEL  org.label-schema.description="A hello world application to demonstrate Prometheus Java Spring Boot instrumentation." \
       org.label-schema.usage="docker run -d kubernauts/prometheus_example:instrumented" \
       org.label-schema.docker.cmd="docker run -d kubernauts/prometheus_example:instrumented" \
       org.label-schema.build-date=$BUILD_DATE \
       org.label-schema.name="kubernauts/prometheus_example" \
       org.label-schema.schema-version="1.0.0-rc.1" \
       org.label-schema.url="https://github.com/kubernauts/prometheus_example" \
       org.label-schema.vcs-ref=$VCS_REF \
       org.label-schema.vcs-url="https://github.com/kubernauts/prometheus_example.git" \
       org.label-schema.vendor="kubernauts"

EXPOSE 8080

COPY --from=builder /prometheus_example/springboot/instrumented/target/prometheus_example-*-SNAPSHOT.jar /instrumented.jar

ENTRYPOINT java -jar /instrumented.jar
