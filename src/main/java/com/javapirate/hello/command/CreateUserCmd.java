package com.javapirate.hello.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCmd {
    @TargetAggregateIdentifier
    private Integer id;
    private String name;
    private BigInteger initialBalance;
}
