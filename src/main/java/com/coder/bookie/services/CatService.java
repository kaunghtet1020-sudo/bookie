package com.coder.bookie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.bookie.exceptions.CategoryNotFoundException;
import com.coder.bookie.models.Category;
import com.coder.bookie.repos.CatRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatService {
    @Autowired
    private final CatRepo catRepo;

    public List<Category> all() {
        return catRepo.findAll();
    }

    public Category get(Integer id) {
        return catRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("No cat with that id"));
    }
    public void add(Category category){
        catRepo.save(category);
    }

    public void update(Category category) {
        Category dbcat = get(category.getId());
        if (dbcat != null) {
            dbcat.setName(category.getName());
            catRepo.save(dbcat);
        }

    }

    public void drop(Integer id){
        Category dbcat=get(id);
        catRepo.deleteById(dbcat.getId());
    }

  

}
