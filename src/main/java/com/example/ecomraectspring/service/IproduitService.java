package com.example.ecomraectspring.service;

import com.example.ecomraectspring.entity.Produit;

import java.util.List;

public interface IproduitService {
    void ajouter(Produit p);

    Produit selecOne(int id);

    List<Produit> selectAll();

    List<Produit> selectByCategorieId(int categorieId);

    List<Produit> selectTop6();

    List<Produit> selectByName(String name);

    void supprimer(int id);

    void modifier(Produit p);

    List<Produit> findByNomStartingWith(String search);
}
