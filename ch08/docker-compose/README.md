# Using Docker Compose

- Start Up
	```shell
	export DATAFLOW_VERSION=2.5.1.RELEASE
	export SKIPPER_VERSION=2.4.1.RELEASE
	docker-compose -f docker-compose-rabbitmq.yml up
	```
- Shutdown
	```
	docker-compose -f docker-compose-rabbitmq.yml down
	```