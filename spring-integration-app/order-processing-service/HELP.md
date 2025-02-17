# Getting Started

### Setup Kafka server with docker compose
$ docker compose -f kafka.yaml up -d

### Validate messages sent to kafka
$ docker exec -it kafka-broker-1 bash
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic notification-topic --from-beginning
