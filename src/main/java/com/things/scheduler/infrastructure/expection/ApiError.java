package com.things.scheduler.infrastructure.expection;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String type;
    private String message;
    private int status;

}
