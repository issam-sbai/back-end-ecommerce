package com.example.ecomraectspring.repo;

import com.example.ecomraectspring.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit,Integer> {
    List<Produit> findByCategorieId(int categorieId);
    List<Produit> findTop6ByOrderByNom(); // Replace "SomeField" with the actual field to order by
    List<Produit> findByNomContaining(String nom);
}
