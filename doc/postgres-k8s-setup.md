# Postgres

## Docker

```
https://hub.docker.com/_/postgres
```

### Install

docker pull postgres

### Run

docker run --name disk-postgres -e POSTGRES_PASSWORD=disk#123 -d postgres

### PSQL

docker exec -it disk-postgres /bin/bash

psql -U postgres

postgres=# select 1;
 ?column?
----------
        1
(1 row)

## K8S

```
kubectl apply -f postgres.yaml
kubectl delete -f postgres.yaml
```