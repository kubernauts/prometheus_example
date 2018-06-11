# Prometheus Examples

The purpose of this repository is to provide various Prometheus instrumentation examples.


### Spring Boot
---
The Spring Boot example consists of four Docker image tags:

* *latest*: It is built from the [springboot/Dockerfile](springboot/Dockerfile) which consists the `instrumented`, `uninstrumented` and `jmx_exporter` jar files. When using this image make sure to specify one of the following options to choose the jar you want to run:
    * uninstrumented
    * instrumented
    * jmx
    
    Usage Examples: 
    * docker run -d -p 80:8080 -p 8080:8888 prometheus_example:latest jmx
    * docker run -d -p 80:8080 prometheus_example:latest instrumented
    * docker run -d -p 80:8080 prometheus_example:latest uninstrumented

* *uninstrumented*: It is built from the [springboot/uninstrumented/Dockerfile](springboot/uninstrumented/Dockerfile) which consists just the `uninstrumented` jar file. 

    Usage Example:
    * docker run -d -p 80:8080 prometheus_example:uninstrumented

* *instrumented*: It is built from the [springboot/instrumented/Dockerfile](springboot/instrumented/Dockerfile) which consists just the `instrumented` jar file. 

    Usage Example:
    * docker run -d -p 80:8080 prometheus_example:instrumented

* *jmx*: It is built from the [springboot/uninstrumented/Dockerfile.jmx](springboot/uninstrumented/Dockerfile.jmx) which consists just the `uninstrumented` and `jmx_exporter` jar files.

    Usage Example:
    * docker run -d -p 80:8080 -p 8080:8888 prometheus_example:jmx
