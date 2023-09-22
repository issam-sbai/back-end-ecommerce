package com.example.ecomraectspring.service;

import com.example.ecomraectspring.entity.Produit;
import com.example.ecomraectspring.repo.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitService implements IproduitService {

    private final ProduitRepo produitRepository;

    @Autowired
    public ProduitService(ProduitRepo produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public void ajouter(Produit p) {
        produitRepository.save(p);
    }

    @Override
    public Produit selecOne(int id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> selectAll() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> selectByCategorieId(int categorieId) {
        return produitRepository.findByCategorieId(categorieId);
    }

    @Override
    public List<Produit> selectTop6() {
        return produitRepository.findTop6ByOrderByNom();
    }

    @Override
    public List<Produit> selectByName(String name) {
        return produitRepository.findByNomContaining(name);
    }

    @Override
    public void supprimer(int id) {
        produitRepository.deleteById(id);
    }

    @Override
    public void modifier(Produit p) {
        produitRepository.save(p);
    }

    @Override
    public List<Produit> findByNomStartingWith(String search) {
        return produitRepository.findByNomContaining(search);
    }
}
