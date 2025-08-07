package com.coder.bookie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.bookie.models.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

}
