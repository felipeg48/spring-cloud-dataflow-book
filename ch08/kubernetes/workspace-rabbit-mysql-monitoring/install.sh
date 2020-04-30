#!/bin/sh


if type jq &> /dev/null; then
	echo "jq .... OK"
else
	echo "You need to have 'jq' installed: https://stedolan.github.io/jq/"
	exit 1 
fi


echo 'Installing Spring Cloud Data Flow Server...\n'
kubectl create -f rabbitmq/
kubectl create -f mysql/
echo 'Installing Grafana and Prometheus...\n'
kubectl create -f prometheus/prometheus-clusterroles.yaml
kubectl create -f prometheus/prometheus-clusterrolebinding.yaml
kubectl create -f prometheus/prometheus-serviceaccount.yaml

kubectl create -f prometheus/prometheus-configmap.yaml
kubectl create -f prometheus/prometheus-deployment.yaml
kubectl create -f prometheus/prometheus-service.yaml

if [ -z "$1" ]; then
	kubectl create -f grafana/
else
	kubectl create -f grafana/grafana-secret.yaml
	kubectl create -f grafana/grafana-configmap.yaml
	kubectl create -f grafana/grafana-deployment.yaml
	sed 's/LoadBalancer/NodePort/' grafana/grafana-service.yaml | kubectl create -f-
fi 

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

if [ -z "$1" ]; then
	kubectl create -f server/server-config.yaml
else
	 kubectl wait --for=condition=Ready pod -l app=grafana --timeout=180s
	 #GRAFANA=$(minikube service grafana --url)
	 #sed "s|https://grafana:3000|${URL}|" server/server-config.yaml | kubectl create -f-

	 GRAFANA=$(kubectl cluster-info | awk 'NR==1 {print $NF}'| sed 's/\(.*\):/\1 /' | awk '{print $1}' | sed 's/https/http/' | sed -E "s/"$'\E'"\[([0-9]{1,3}((;[0-9]{1,3})*)?)?[m|K]//g" )
	 PORT=$(kubectl get svc -l app=grafana -o json | jq '.items[0].spec.ports[0].nodePort')
	 URL="$GRAFANA:$PORT"
	 echo "\nSetting Grafana URL: $URL"
	 EXPR="s|https://grafana:3000|"$URL"|"
	 sed -e $EXPR server/server-config.yaml | kubectl create -f-
fi

if [ -z "$1" ]; then 
	kubectl create -f server/server-svc.yaml
else
	sed 's/LoadBalancer/NodePort/;1,/80/s//8080/' server/server-svc.yaml | kubectl create -f- 
fi

kubectl create -f server/server-deployment.yaml
echo '\nWaiting a little bit for Spring Cloud Data Flow Server to start ...\n'
kubectl wait --for=condition=Ready pod -l app=scdf-server --timeout=180s
echo '\nDone.'

SCDF=$(kubectl cluster-info | awk 'NR==1 {print $NF}'| sed 's/\(.*\):/\1 /' | awk '{print $1}' | sed 's/https/http/' )
PORT=$(kubectl get svc -l app=scdf-server -o json | jq '.items[0].spec.ports[0].nodePort')
URL="$SCDF:$PORT/dashboard"
echo "\n Spring Cloud Data Flow Server: $URL \n"