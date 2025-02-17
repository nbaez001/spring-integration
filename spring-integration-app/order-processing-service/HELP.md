# Getting Started

### Setup Kafka server with docker compose
$ docker compose -f kafka.yaml up -d

### Validate messages sent to kafka
$ docker exec -it kafka-broker-1 bash
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic notification-topic --from-beginning

### Endpoint
curl --location 'localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data '{
"orderId": "2",
"items": [
{
"itemId": "1",
"itemType": "ELECTRONICS",
"quantity": 2
},
{
"itemId": "2",
"itemType": "CLOTHING",
"quantity": 3
},
{
"itemId": "3",
"itemType": "GROCERIES",
"quantity": 7
},
{
"itemId": "4",
"itemType": "ELECTRONICS",
"quantity": 3
}
]
}'