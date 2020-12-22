package com.javapirate.hello.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreditModel {
    private Integer id;
    private BigInteger creditAmount;
}
