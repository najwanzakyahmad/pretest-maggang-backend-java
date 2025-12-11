package com.tokoadmin.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;      
    private String message; 
    private T data;     

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }
}