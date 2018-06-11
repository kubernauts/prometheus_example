FROM maven:latest as builder

ARG REPO='https://github.com/kubernauts/prometheus_example.git'
ARG $BUILD_DATE
ARG $VCS_REF

WORKDIR /prometheus_example

RUN git clone $REPO . && cd springboot/uninstrumented && mvn package

FROM openjdk:8-jdk-slim

ARG BUILD_DATE
ARG VCS_REF

LABEL  org.label-schema.description="A hello world application to demonstrate Prometheus Java Spring Boot instrumentation." \
       org.label-schema.usage="docker run -d -p :8080 kubernautslabs/prometheus_example:uninstrumented" \
       org.label-schema.docker.cmd="docker run -d -p :8080 kubernautslabs/prometheus_example:uninstrumented" \
       org.label-schema.build-date=$BUILD_DATE \
       org.label-schema.name="kubernauts/prometheus_example" \
       org.label-schema.schema-version="1.0.0-rc.1" \
       org.label-schema.url="https://github.com/kubernauts/prometheus_example" \
       org.label-schema.vcs-ref=$VCS_REF \
       org.label-schema.vcs-url="https://github.com/kubernauts/prometheus_example.git" \
       org.label-schema.vendor="kubernauts"

EXPOSE 8080

COPY --from=builder /prometheus_example/springboot/uninstrumented/target/prometheus_example-*-SNAPSHOT.jar /uninstrumented.jar

ENTRYPOINT java -jar /uninstrumented.jar
