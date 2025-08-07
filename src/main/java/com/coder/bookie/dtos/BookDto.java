package com.coder.bookie.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String publicDate;
    private String description;
    private MultipartFile file;
    private double price;

    private Integer stock;
    private Integer catid;          // private Category category in models

}
