package com.coder.bookie.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class CatDto {
private String name;
private MultipartFile file;
}
