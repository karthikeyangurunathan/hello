package com.javapirate.hello.api.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class UserModel {
    private Integer id;
    private String name;
    private BigInteger initialBalance;
}
