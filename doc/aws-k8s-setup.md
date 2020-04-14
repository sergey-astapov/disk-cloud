# AWS K8S Setup

## Vagrant

```
brew cask install vagrant
```

**Bootstrup VM**

```
mkdir ubuntu
cd ubuntu/
vagrant init ubuntu/xenial64
vagrant up
vagrant ssh-config
vagrant ssh
```

## KOPS

```
htpps://github.com/kubernetes/kops
```

*Releases*

```
https://github.com/kubernetes/kops/releases
https://github.com/kubernetes/kops/releases/latest
```

### Installation

```
vagrant ssh
wget https://github.com/kubernetes/kops/releases/download/v1.16.0/kops-linux-amd64
chmod +x kops-linux-amd64
sudo mv kops-linux-amd64 /usr/local/bin/
sudo mv /usr/local/bin/kops-linux-amd64 /usr/local/bin/kops

sudo apt-get install python-pip
cat /etc/apt/sources.list
grep '^deb ' /etc/apt/sources.list
sudo vi /etc/apt/sources.list

add
---
deb http://archive.ubuntu.com/ubuntu xenial main universe
deb http://archive.ubuntu.com/ubuntu xenial-updates main universe
deb http://archive.ubuntu.com/ubuntu xenial-updates universe

sudo apt update
sudo apt-get install python-pip
export LC_ALL=C
sudo pip install awscli
```

[AWS Setup IAM](#iam)

```
aws configure
AWS Access Key ID [None]: ***
AWS Secret Access Key [None]: ***
Default region name [None]:
Default output format [None]:

ls -ahl ~/.aws/
total 16K
drwxrwxr-x 2 vagrant vagrant 4.0K Mar 25 07:38 .
drwxr-xr-x 5 vagrant vagrant 4.0K Mar 25 07:38 ..
-rw------- 1 vagrant vagrant   10 Mar 25 07:38 config
-rw------- 1 vagrant vagrant  116 Mar 25 07:38 credentials
```

[AWS S3](#s3)

[AWS Route 53](#route-53)

[Namecheap Name Service Setup](#namecheap)

```
wget https://storage.googleapis.com/kubernetes-release/release/v1.17.0/bin/linux/amd64/kubectl
sudo mv kubectl /usr/local/bin
sudo chmod +x /usr/local/bin/kubectl
```

[Setup Priv/Pub Keys](#priv/pub-key)

```
aws ec2 describe-availability-zones --region ap-southeast-1 | grep ZoneName
"ZoneName": "ap-southeast-1b"
```


```
kops create cluster --name=k8s.devtools4.me \
 --state=s3://kops-state-sg-250320-15-43 \
 --zones=ap-southeast-1b \
 --node-count=2 \
 --node-size=t2.micro \
 --master-size=t2.micro \
 --dns-zone=k8s.devtools4.me

kops update cluster k8s.devtools4.me \
 --yes \
 --state=s3://kops-state-sg-250320-15-43
```

#### Validate K8S Cluster

```
kops validate cluster --state=s3://kops-state-sg-250320-15-43
kubectl get nodes
```

| NAME | STATUS | ROLES | AGE | VERSION |
|---|---|---|---|---|
|ip-172-20-38-209.ap-southeast-1.compute.internal|Ready|master|12m|v1.16.7|
|ip-172-20-39-49.ap-southeast-1.compute.internal|Ready|node|10m|v1.16.7|
|ip-172-20-44-31.ap-southeast-1.compute.internal|Ready|node|11m|v1.16.7|

#### Priv/Pub Key

```
ls .ssh/
ssh-keygen -f .ssh/id_rsa
cat .ssh/id_rsa.pub
cat .ssh/id_rsa
```

## AWS

### IAM

[Identity and Access Management](https://console.aws.amazon.com/iam/home)

*Users > Add user > kops*

*Access type > Programmatic access> Create group*

Group name: Administrators

*Filter*: AWS managed -job function to filter the table contents > select AdministratorAccess

|User |Access key ID |Secret access key|
|---|---|---|
|kops | *** | *** |

### S3

*Create bucket > kops-state-sg-250320-15-43*

### Route 53

*DNS Management > Create Hosted Zone > k8s.devtools4.me*

|Name | Name Services |
|---|---|
|k8s.devtools4.me | ns-648.awsdns-17.net.
|                 | ns-1663.awsdns-15.co.uk.
|                 | ns-296.awsdns-37.com.
|                 | ns-1274.awsdns-31.org.

## Namecheap

www.namecheap.com

*Domain List > devtool4.me > Manage > Advanced DNS > Add New Record > NS Record*

|Host | Value |
|---|---|
| k8s  | ns-648.awsdns-17.net |
| k8s  | ns-1663.awsdns-15.co.uk |
| k8s  | ns-296.awsdns-37.com |
| k8s  | ns-1274.awsdns-31.org |

## Docker

```
sudo apt-get remove docker docker-engine docker.io containerd runc
sudo apt-get update
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io
sudo usermod -G docker vagrant

exit
vagrant ssh

docker
docker --version
```

## Java

```
sudo apt install openjdk-8-jdk
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

## Maven

```
wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -xvf apache-maven-3.6.3-bin.tar.gz
mv apache-maven-3.6.3 apache-maven

sudo mv apache-maven /opt
export PATH=/opt/apache-maven/bin:$PATH
```