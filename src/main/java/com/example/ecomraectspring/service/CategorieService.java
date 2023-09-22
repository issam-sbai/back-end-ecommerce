package com.example.ecomraectspring.service;

import com.example.ecomraectspring.entity.Categorie;
import com.example.ecomraectspring.repo.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorieService implements IcategorieService {

    private final CategorieRepo categorieRepository;

    @Autowired
    public CategorieService(CategorieRepo categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public void inserer(Categorie c) {
        categorieRepository.save(c);
    }

    @Override
    public Categorie selectById(int id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categorie> selectAll() {
        return categorieRepository.findAll();
    }

    @Override
    public void delete(int id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public void update(Categorie c) {
        categorieRepository.save(c);
    }
}
