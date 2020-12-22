package com.javapirate.hello.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class UserCreditedEvent {
    
    @TargetAggregateIdentifier
    private Integer id;
    private BigInteger creditedAmount;
}
