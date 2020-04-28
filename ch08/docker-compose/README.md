# Using Docker Compose

- Start Up
	```shell
	$ export DATAFLOW_VERSION=2.4.2.RELEASE
	$ export SKIPPER_VERSION=2.3.2.RELEASE
	$ docker-compose -f docker-compose-rabbitmq.yml up
	```
- ShutDowm
	```
	$ docker-compose -f docker-compose-rabbitmq.yml down
	```