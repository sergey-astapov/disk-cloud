FROM centos

RUN yum update -y
RUN yum install -y wget

RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel

ENV JAVA_HOME /etc/alternatives/jre

ADD target/disk-cloud-service-0.0.1-SNAPSHOT.jar /usr/local/apps/disk-cloud-service.jar

EXPOSE 8080 8080

ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m", "-jar", "/usr/local/apps/disk-cloud-service.jar", "-Dspring.profiles.active=sit"]