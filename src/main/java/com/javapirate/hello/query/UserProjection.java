package com.javapirate.hello.query;

import com.javapirate.hello.command.UserCreatedEvent;
import com.javapirate.hello.command.UserCreditedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UserProjection {
    private static Map<Integer, UserEntity> users = new HashMap<>();

    @EventHandler
    private void handleUserCreated(UserCreatedEvent event) {
        log.info("Projection created new user : {} ", event);
        users.put(event.getId(), new UserEntity(event.getId(), event.getName(), event.getInitialBalance()));
    }

    @EventHandler
    private void handleUserCredited(UserCreditedEvent event) {
        log.info("Projection user account updated with amount : {}", event);
        UserEntity userEntity = users.get(event.getId());
        userEntity.setBalance(userEntity.getBalance().add(event.getCreditedAmount()));
        users.put(event.getId(), userEntity);
    }

    @QueryHandler
    private UserEntity getUser(UserQuery userQuery) {
        log.info("Returning result for userquery : {}", userQuery);
        UserEntity userEntity = users.get(userQuery.getId());
        log.info("USer fetched for request : {} ", userEntity );
        return userEntity;
    }
}
