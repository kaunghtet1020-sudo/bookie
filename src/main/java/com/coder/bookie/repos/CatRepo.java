package com.coder.bookie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.bookie.models.Category;
@Repository
public interface CatRepo extends JpaRepository<Category,Integer>{

}
