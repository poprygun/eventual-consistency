package io.pivotal.ec;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MessagesChannelSink.class)
public class ConsumerConfiguration {
}
