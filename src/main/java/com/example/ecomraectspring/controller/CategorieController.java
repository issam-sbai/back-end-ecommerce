package com.example.ecomraectspring.controller;

import com.example.ecomraectspring.entity.Categorie;
import com.example.ecomraectspring.service.IcategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategorieController {

    @Autowired
    private IcategorieService service;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Categorie getCategoryById(@PathVariable int id) {
        return service.selectById(id);
    }

    @PostMapping
    public void addCategory(@RequestBody Categorie categorie) {
        service.inserer(categorie);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody Categorie categorie) {
        Categorie existingCategory = service.selectById(id);
        if (existingCategory != null) {
            existingCategory.setNom(categorie.getNom());
            service.update(existingCategory);
        } else {
            // Handle the case where the category with the provided ID doesn't exist
            throw new RuntimeException("Category not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        service.delete(id);
    }
}
