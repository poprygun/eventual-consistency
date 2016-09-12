package io.pivotal.ec;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

@EnableBinding(MessageSource.class)
@IntegrationComponentScan
public class ProducerConfiguration {
}
