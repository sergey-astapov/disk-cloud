FROM centos

RUN yum update -y
RUN yum install -y wget

RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel

ENV JAVA_HOME /etc/alternatives/jre

ADD target/disk-cloud-service-0.0.1-SNAPSHOT.jar /usr/local/apps/disk-cloud-service.jar

RUN wget \
 http://search.maven.org/remotecontent?filepath=org/jolokia/jolokia-jvm/1.6.2/jolokia-jvm-1.6.2-agent.jar -O \
 /usr/local/apps/jolokia-jvm-agent.jar

EXPOSE 8080 8778

ENTRYPOINT ["java", \
 "-Xms256m", "-Xmx1024m", \
 "-javaagent:/usr/local/apps/jolokia-jvm-agent.jar=port=8778,host=0.0.0.0", \
 "-jar", \
 "/usr/local/apps/disk-cloud-service.jar", \
 "-Dspring.profiles.active=sit" \
]