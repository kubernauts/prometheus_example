#!/usr/bin/env sh

case "$1" in
    uninstrumented)
        java -jar /uninstrumented.jar
        ;;
    instrumented)
        java -jar /instrumented.jar
        ;;
    jmx)
        sed -i 's/5555/8080/g' /config.yml && java -javaagent:/jmx_exporter.jar=8888:/config.yml -jar /uninstrumented.jar
        ;;
    *)
        echo "Please provide a valid argement: \n\tuninstrumented\n\tinstrumented\n\tjmx\nThank you."
        ;;
esac
