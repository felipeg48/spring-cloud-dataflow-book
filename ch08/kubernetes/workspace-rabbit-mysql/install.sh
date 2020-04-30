#!/bin/sh

echo 'Installing Spring Cloud Data Flow Server...\n'
kubectl create -f rabbitmq/
kubectl create -f mysql/
kubectl create -f server/server-roles.yaml
kubectl create -f server/server-rolebinding.yaml
kubectl create -f server/service-account.yaml
kubectl create -f skipper/skipper-config-rabbit.yaml
kubectl create -f skipper/skipper-deployment.yaml
if [ -z "$1" ]; then 
	kubectl create -f skipper/skipper-svc.yaml 
else 
	sed 's/LoadBalancer/NodePort/;s/80/7577/' skipper/skipper-svc.yaml | kubectl create -f- 
fi
echo '\nWaiting a little bit for Skipper to start ...\n'
kubectl wait --for=condition=Ready pod -l app=skipper --timeout=180s
kubectl create -f server/server-config.yaml
if [ -z "$1" ]; then 
	kubectl create -f server/server-svc.yaml
else
	sed 's/LoadBalancer/NodePort/;1,/80/s//8080/' server/server-svc.yaml | kubectl create -f- 
fi
kubectl create -f server/server-deployment.yaml
echo '\nWaiting a little bit for Spring Cloud Data Flow Server to start ...\n'
kubectl wait --for=condition=Ready pod -l app=scdf-server --timeout=180s
echo '\nDone.'