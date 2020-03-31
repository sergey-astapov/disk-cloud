# Disk Cloud

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

### K8S

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
curl http://54.251.142.28:32564/swagger-ui.html
```

### On-Premise

#### Disk Cloud API On-Premise Installation

```
```

## Swagger UI

```
http://host:port/swagger-ui.html
```