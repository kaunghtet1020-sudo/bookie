package com.coder.bookie.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_name",nullable = false,unique = true) //dbထဲမှာ book_name ဆိုတဲ့ columnတခုဖန်တီးမယ် 
    private String name;
    @Column(nullable = false)
    private String image;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)                                 //categoryတခုမှာbookတွေအများကြီးရှိနိုင်တယ် lazy=ခဏစောင့်ယူ
    @JsonBackReference
    private Set<Book>books=new HashSet<>();

    @Override
    public String toString(){
        return "Category { " +
        "id = " + id + "\'" + 
        "name =" + name + "\'" +
        "image = " + image +
         "}";
    }

  


}
