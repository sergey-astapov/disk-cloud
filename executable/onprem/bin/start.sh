#!/bin/sh

JAVA=java
JVM_OPTS="-Xms256m -Xmx1024m"

nohup $JAVA $JVM_OPTS -jar \
disk-cloud-service.jar \
-Dspring.profiles.active=sit