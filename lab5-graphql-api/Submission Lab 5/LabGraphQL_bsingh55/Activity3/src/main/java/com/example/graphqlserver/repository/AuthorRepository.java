package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long>{
    
}
