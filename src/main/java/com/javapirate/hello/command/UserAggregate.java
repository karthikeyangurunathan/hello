package com.javapirate.hello.command;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigInteger;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class UserAggregate {
    
    @AggregateIdentifier
    private Integer id;
    private String name;
    private BigInteger balance;
    
    protected UserAggregate(){}
    
    @CommandHandler
    public UserAggregate(CreateUserCmd cmd){
        log.info("New createUser command received : {}" , cmd);
        apply(new UserCreatedEvent(cmd.getId(),cmd.getName(),cmd.getInitialBalance()));
    }
    
    @CommandHandler
    private void handleCreditUserCommand(CreditUserCmd cmd){
        log.info("User account credit command request : {}",cmd);
        apply(new UserCreditedEvent(cmd.getId(),cmd.getCreditAmount()));
    }
    
    @EventSourcingHandler
    private void handleUserCreatedEvent(UserCreatedEvent event){
        log.info("New userCreated event : {}",event);
        this.id = event.getId();
        this.name = event.getName();
        this.balance = event.getInitialBalance();
    }
    
    @EventSourcingHandler
    private void handleUserCreditedEvent(UserCreditedEvent event){
        log.info("User account credit with the amount : {}",event);
        this.balance = balance.add(event.getCreditedAmount());
    }
}
