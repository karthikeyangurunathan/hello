package com.javapirate.hello.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class UserCreatedEvent {
    @TargetAggregateIdentifier
    private final Integer id;
    private final String name;
    private final BigInteger initialBalance;
}
