FROM maven:latest as builder

ARG REPO='https://github.com/kubernauts/prometheus_example.git'

WORKDIR /prometheus_example

RUN git clone $REPO . 
RUN mvn package -f springboot/uninstrumented/pom.xml 
RUN mvn package -f springboot/instrumented/pom.xml

FROM maven:latest as jmx

ARG REPO='https://github.com/prometheus/jmx_exporter.git'

WORKDIR /jmx_exporter

RUN git clone $REPO . && mvn package

FROM openjdk:8-jdk-slim

LABEL  org.label-schema.description="A hello world application to demonstrate Prometheus Java Spring Boot instrumentation." \
       org.label-schema.usage="docker run -d kubernauts/prometheus_example:uninstrumented" \
       org.label-schema.docker.cmd="docker run -d kubernauts/prometheus_example:uninstrumented" \
       org.label-schema.build-date=$BUILD_DATE \
       org.label-schema.name="kubernauts/prometheus_example" \
       org.label-schema.schema-version="1.0.0-rc.1" \
       org.label-schema.url="https://github.com/kubernauts/prometheus_example" \
       org.label-schema.vcs-ref=$VCS_REF \
       org.label-schema.vcs-url="https://github.com/kubernauts/prometheus_example.git" \
       org.label-schema.vendor="kubernauts"

EXPOSE 8080 5555

COPY --from=builder /prometheus_example/springboot/uninstrumented/target/prometheus_example-*-SNAPSHOT.jar /uninstrumented.jar
COPY --from=builder /prometheus_example/springboot/instrumented/target/prometheus_example-*-SNAPSHOT.jar /instrumented.jar
COPY --from=jmx /jmx_exporter/jmx_prometheus_javaagent/target/jmx_prometheus_javaagent-*-SNAPSHOT.jar /jmx_exporter.jar
COPY --from=jmx /jmx_exporter/example_configs/httpserver_sample_config.yml /config.yml
COPY ./starter.sh /starter.sh
RUN chmod +x /starter.sh

CMD []
ENTRYPOINT ["/starter.sh"]
