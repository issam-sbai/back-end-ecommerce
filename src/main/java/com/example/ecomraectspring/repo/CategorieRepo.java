package com.example.ecomraectspring.repo;

import com.example.ecomraectspring.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie,Integer> {
}
