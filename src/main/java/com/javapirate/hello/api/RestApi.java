package com.javapirate.hello.api;

import com.javapirate.hello.api.model.UserCreditModel;
import com.javapirate.hello.command.CreateUserCmd;
import com.javapirate.hello.api.model.UserModel;
import com.javapirate.hello.command.CreditUserCmd;
import com.javapirate.hello.query.UserEntity;
import com.javapirate.hello.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class RestApi {
    
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;
    
    @Autowired
    public RestApi(CommandGateway commandGateway,QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }
    
    @PostMapping("/api/user")
    private String createUser(@RequestBody UserModel user){
        log.info("New create user request received for user : {}",user);
        commandGateway.send(new CreateUserCmd(user.getId(),user.getName(),user.getInitialBalance()));
        return "saved";
    }
    
    @PostMapping("/api/user/credit")
    private String creditUserAccount(@RequestBody UserCreditModel user){
        log.info("User credit request received : {} ", user);
        commandGateway.send(new CreditUserCmd(user.getId(),user.getCreditAmount()));
        return "Amount credited";
    }
    
    @GetMapping("/user/{id}")
    private UserEntity getUser(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        log.info("Get user request for : {}",id);
        UserEntity userResult = queryGateway.query(new UserQuery(id), UserEntity.class).get();
        return userResult;
    }
}
