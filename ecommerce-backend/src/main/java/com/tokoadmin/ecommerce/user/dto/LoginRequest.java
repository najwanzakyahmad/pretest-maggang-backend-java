package com.tokoadmin.ecommerce.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
