# Eventual Consistency - Spring Boot Stream AMQP Example

Producer project sends message to amqp in response to get to http://client-service.cfapps.pez.pivotal.io/messages.
Message is serialized to JSON format.
Consumer prints out the contents of the message.  Consumer property durableSubscription: is set to true, and messages survives consumer crash.

### Create PCF RabbitMQ service

```bash
cf create-service p-rabbitmq standard mq-service
```
### [Using Contract Verifier](https://cloud.spring.io/spring-cloud-contract/spring-cloud-contract.html) for Producer project.

resources/contracts/*.groovy files are used to generate contract test files in target/generated-test-sources/contracts folder


