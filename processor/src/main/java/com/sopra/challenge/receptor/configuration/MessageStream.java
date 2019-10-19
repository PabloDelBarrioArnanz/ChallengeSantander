package com.sopra.challenge.receptor.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageStream {

    String INPUT = "orders";
    String OUTPUT_SAVED = "SAVED";
    String OUTPUT_PROCESSED = "PROCESSED";
    String OUTPUT_KO = "KO";

    @Input(INPUT)
    SubscribableChannel inboundOrders();

    @Output(OUTPUT_SAVED)
    MessageChannel outboundSaved();

    @Output(OUTPUT_PROCESSED)
    MessageChannel outboundProcessed();

    @Output(OUTPUT_KO)
    MessageChannel outboundKO();

}
