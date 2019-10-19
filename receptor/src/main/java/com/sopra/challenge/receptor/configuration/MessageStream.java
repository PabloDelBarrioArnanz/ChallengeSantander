package com.sopra.challenge.receptor.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageStream {

    String INPUT_SAVED = "SAVED";
    String INPUT_PROCESSED = "PROCESSED";
    String INPUT_KO = "KO";
    String OUTPUT = "orders";

    @Input(INPUT_SAVED)
    SubscribableChannel inboundSaved();

    @Input(INPUT_PROCESSED)
    SubscribableChannel inboundProcessed();

    @Input(INPUT_KO)
    SubscribableChannel inboundKO();

    @Output(OUTPUT)
    MessageChannel outboundOrders();

}
