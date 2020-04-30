# Kubernetes

## Spring Cloud Data Flow installation

The versions used are:

- Spring Cloud Data Flow: 2.4.2.RELEASE
- Spring Cloud Skipper: 2.3.2.RELEASE

1. Go into the folder and execute the following commands:

- Start Up
	```shell
	kubectl create -f rabbitmq/
	kubectl create -f mysql/
	kubectl create -f server/server-roles.yaml
	kubectl create -f server/server-rolebinding.yaml
	kubectl create -f server/service-account.yaml
	kubectl create -f skipper/skipper-config-rabbit.yaml
	kubectl create -f skipper/skipper-deployment.yaml
	kubectl create -f skipper/skipper-svc.yaml
	kubectl create -f server/server-config.yaml
	kubectl create -f server/server-svc.yaml
	kubectl create -f server/server-deployment.yaml
	```
- Shutdown
	```shell
	kubectl delete -f server/server-deployment.yaml
	kubectl delete -f server/server-svc.yaml
	kubectl delete -f server/server-config.yaml
	kubectl delete -f skipper/skipper-svc.yaml
	kubectl delete -f skipper/skipper-deployment.yaml
	kubectl delete -f skipper/skipper-config-rabbit.yaml
	kubectl delete -f server/service-account.yaml
	kubectl delete -f server/server-rolebinding.yaml
	kubectl delete -f server/server-roles.yaml
	kubectl delete -f mysql/	
	kubectl delete -f rabbitmq/
	```

> Each folder has the `install.sh` and `uninstall.sh` scripts.

## Minikube

- Mac OS
   ```shell
   minikube start --driver=hyperkit --cpus=4 --memory='12288m' --kubernetes-version='1.16.0'
   ```