package com.coder.bookie.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Msg {
    String msg;
    HttpStatus status;

}
