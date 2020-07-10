# Using Docker Compose

These files have also **NATs** server included.

- Start Up
	```shell
	export DATAFLOW_VERSION=2.5.0.RELEASE
	export SKIPPER_VERSION=2.4.0.RELEASE
	docker-compose -f docker-compose-rabbitmq.yml up
	```
- Shutdown
	```
	docker-compose -f docker-compose-rabbitmq.yml down
	```