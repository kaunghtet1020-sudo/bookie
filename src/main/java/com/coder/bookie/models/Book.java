package com.coder.bookie.models;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publicDate;
    private String description;
    private String image;
    private double price;

    private Integer stock;
    @ManyToOne // bookအများကြီးမှာ categoryတစ်ခုတည်း
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private Category category;

}
