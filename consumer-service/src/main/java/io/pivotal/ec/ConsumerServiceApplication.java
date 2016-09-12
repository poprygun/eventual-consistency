package io.pivotal.ec;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class ConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }
}

@Service
@Slf4j
class MessageProcessor {

    @StreamListener(MessagesChannelSink.CHANNEL_NAME)
    public void process(MessageUnit messageUnit) {
        log.info("Processing - id: {}, message: {}", messageUnit.getId(), messageUnit.getMessage());
    }
}

interface MessagesChannelSink {
    String CHANNEL_NAME = "messagesChannel";

    @Input
    SubscribableChannel messagesChannel();
}

class MessageUnit {
    private final String id;
    private final String message;

    @JsonCreator
    public MessageUnit(@JsonProperty("id") String id,
                       @JsonProperty("message") String message) {
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