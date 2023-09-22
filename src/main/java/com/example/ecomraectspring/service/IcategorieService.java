package com.example.ecomraectspring.service;

import com.example.ecomraectspring.entity.Categorie;

import java.util.List;

public interface IcategorieService {
    void inserer(Categorie c);

    Categorie selectById(int id);

    List<Categorie> selectAll();

    void delete(int id);

    void update(Categorie c);
}
