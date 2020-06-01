# Disk Cloud

[![Travis](https://img.shields.io/travis/sergey-astapov/disk-cloud.svg)](https://travis-ci.org/sergey-astapov/disk-cloud)

Disk Cloud

## Installation

### DockerHub

[Disk Cloud Repository](https://hub.docker.com/repository/docker/devtools4me/disk-cloud)

### Docker build/push

```
cd executable/service
docker login
docker build -t devtools4me/disk-cloud .
docker push devtools4me/disk-cloud
```

#### Docker Centos

```
docker build -f Dockerfile -t centos/disk-cloud .
docker run -p 8080:8080 -p 8778:8778 centos/disk-cloud
```

#### Docker Alpine

```
docker build -f Dockerfile.alpine -t alpine/disk-cloud .
docker run -p 8080:8080 -p 8778:8778 alpine/disk-cloud
```

#### Run HAWTIO

```
java -jar ~/devtools/hawtio/hawtio-app-2.10.0.jar --port 8181
```

### K8S

[AWS K8S Setup](./doc/aws-k8s-setup.md)

#### Disk Cloud API K8S Installation

```
mvn clean install
cd executable/k8s/target
tar -xvf disck-cloud-service-k8s-0.0.1-SNAPSHOT-assembly.tar.gz
cd disk-cloud-service-k8s/
kubectl apply -f configmap-sit.yml
kubectl get configmaps
kubectl describe configmap disk-cloud-service-bootstrap
kubectl apply -f service.yml
kubectl get services
kubectl get pods
kubectl logs disk-cloud-service-69f4f68c94-mll2w
```

[Setup EC2 Security Groups](#ec2-security-groups)

```
curl http://<Master PUB IP-Address>:32564/swagger-ui.html
```

#### EC2 Security Groups

```
kubectl get services
```

|NAME|TYPE|CLUSTER-IP|EXTERNAL-IP|PORT(S)|AGE|
|---|---|---|---|---|---|
|disk-cloud-service|LoadBalancer|100.71.60.186|<pending>|8080:32564/TCP|167m|

**Need to open NodePort=32564**

*Instances > master-ap-southeast-1b.masters.k8s.devtools4.me > Description > Security Groups*

*Add New Rule > Inbound Port=32564 > Any IP address *

```
curl http://<PUB IP-Address>:32564/swagger-ui.html
```

### On-Premise

#### Disk Cloud API On-Premise Installation

```
```

## Swagger UI

```
http://host:port/swagger-ui.html
```