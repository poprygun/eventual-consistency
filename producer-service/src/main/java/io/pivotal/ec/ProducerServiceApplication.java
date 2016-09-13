package io.pivotal.ec;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@SpringBootApplication
public class ProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }
}

interface MessageSource {

    String CHANNEL_NAME = "messagesChannel";

    @Output
    MessageChannel messagesChannel();
}

@MessagingGateway
interface MessageGateway {

    @Gateway(requestChannel = MessageSource.CHANNEL_NAME)
    void generate(MessageUnit messageUnit);
}

class MessageUnit {
    private final String id;
    private final String message;

    @JsonCreator
    public MessageUnit(@JsonProperty("id") String id
            , @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}

@RestController
@Slf4j
class MessagesRestController {

    @RequestMapping(value = "/messages", method = RequestMethod.GET
            , produces=MediaType.APPLICATION_JSON_VALUE)
    public MessageUnit ping() {
        String message = "Processing message sent " + System.currentTimeMillis();
        MessageUnit messageUnit
                = new MessageUnit(UUID.randomUUID().toString()
                , message);
        messageGateway.generate(messageUnit);
        log.info("Sent ======> {}", message);
        return messageUnit;
    }

    @Autowired
    public MessagesRestController(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }


    public MessagesRestController() {
    }

    private MessageGateway messageGateway;
}