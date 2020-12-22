package com.javapirate.hello.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigInteger;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditUserCmd {
    @TargetAggregateIdentifier
    private Integer id;
    private BigInteger creditAmount;
}
