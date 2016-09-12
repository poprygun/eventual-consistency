# Eventual Consistency - Spring Boot Stream AMQP Example

Producer project sends message to amqp in response to get to http://client-service.cfapps.pez.pivotal.io/messages.
Message is serialized to JSON format.
Consumer prints out the contents of the message.  Consumer property durableSubscription: is set to true, and messages survives consumer crash.

### Create PCF RabbitMQ service

```bash
cf create-service p-rabbitmq standard mq-service
```

