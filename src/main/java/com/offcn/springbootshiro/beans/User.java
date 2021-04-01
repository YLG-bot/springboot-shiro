package com.offcn.springbootshiro.beans;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class User {

    private Integer userId;
    private String userName;
    private String userPwd;
    private String pwdSalt;
}
