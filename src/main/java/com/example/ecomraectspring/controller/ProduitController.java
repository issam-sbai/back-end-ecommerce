package com.example.ecomraectspring.controller;

import com.example.ecomraectspring.entity.Categorie;
import com.example.ecomraectspring.entity.Produit;
import com.example.ecomraectspring.service.IcategorieService;
import com.example.ecomraectspring.service.IproduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin("http://localhost:5173")
public class ProduitController {

    @Autowired
    private IcategorieService serviceCategorie;
    private final IproduitService service;

    @Autowired
    public ProduitController(IproduitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produit> getProduits(@RequestParam(value = "search", required = false) String search) {
        if (search == null) {
            return service.selectAll();
        } else {
            return service.findByNomStartingWith(search);
        }
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable int id) {
        return service.selecOne(id);
    }


    @PostMapping("/user")
    public void addProduit(
            @RequestParam String nom,
            @RequestParam double prix,
            @RequestParam String description,
            @RequestParam int quantite,
            @RequestParam int categorie, // Use the selected category's ID
            @RequestParam String imageUrl) {

        // Check if the selected category exists
        Categorie categorie1 = serviceCategorie.selectById(categorie);

        if (categorie1 != null) {
            // The selected category exists
            // Create a new product
            Produit newProduct = new Produit();
            newProduct.setNom(nom);
            newProduct.setPrix(prix);
            newProduct.setDescription(description);
            newProduct.setQuantite(quantite);
            newProduct.setCategorie(categorie1);
            newProduct.setImage(imageUrl);

            // Save the new product
            service.ajouter(newProduct);
        } else {
            // Handle the case where the category with the provided ID doesn't exist
            // You can show an error message or take appropriate action
            throw new RuntimeException("Category not found");
        }
    }



    @PostMapping("/api/produits")
    public void newUser(@RequestBody Produit newUser){
        service.ajouter(newUser);
    }


    @PostMapping
    public void addProduit(
            @RequestBody Map<String, Object> requestParams
    ) {
        String nom = (String) requestParams.get("nom");
        double prix = (double) requestParams.get("prix");
        String description = (String) requestParams.get("description");
        int quantite = (int) requestParams.get("quantite");
        int categorie = (int) requestParams.get("categorie");
        String imageUrl = (String) requestParams.get("imageUrl");

        // Check if the selected category exists
        Categorie categorie1 = serviceCategorie.selectById(categorie);

        if (categorie1 != null) {

            Produit newProduct = new Produit();
            newProduct.setNom(nom);
            newProduct.setPrix(prix);
            newProduct.setDescription(description);
            newProduct.setQuantite(quantite);
            newProduct.setCategorie(categorie1);
            newProduct.setImage(imageUrl);

            // Save the new product
            service.ajouter(newProduct);
        } else {
            // Handle the case where the category with the provided ID doesn't exist
            // You can show an error message or take appropriate action
            throw new RuntimeException("Category not found");
        }
    }


    @PutMapping
    public void updateProduit(
            @PathVariable int id,
            @RequestParam String nom,
            @RequestParam double prix,
            @RequestParam String description,
            @RequestParam int quantite,
            @RequestParam int categorie, // Use the selected category's ID
            @RequestParam String imageUrl) {

        // Retrieve the existing product from the database based on its ID
        Produit existingProduct = service.selecOne(id);

        if (existingProduct != null) {
            // Update the fields of the existing product with the new values
            existingProduct.setNom(nom);
            existingProduct.setPrix(prix);
            existingProduct.setDescription(description);
            existingProduct.setQuantite(quantite);

            // Retrieve the selected category
            Categorie categorie1 = serviceCategorie.selectById(categorie);

            if (categorie1 != null) {
                // Set the product's category
                existingProduct.setCategorie(categorie1);
            } else {
                // Handle the case where the category with the provided ID doesn't exist
                // You can show an error message or take appropriate action
                throw new RuntimeException("Category not found");
            }

            existingProduct.setImage(imageUrl);

            // Save the updated product
            service.modifier(existingProduct);
        } else {
            // Handle the case where the product with the provided ID doesn't exist
            // You can show an error message or take appropriate action
            throw new RuntimeException("Product not found");
        }
    }


    @PutMapping("/{id}")
    public void updateProduit(
            @PathVariable int id,
            @RequestBody Map<String, Object> requestParams) {

        // Retrieve the existing product from the database based on its ID
        Produit existingProduct = service.selecOne(id);

        if (existingProduct != null) {
            // Update the fields of the existing product with the new values
            if (requestParams.containsKey("nom")) {
                existingProduct.setNom((String) requestParams.get("nom"));
            }
            if (requestParams.containsKey("prix")) {
                existingProduct.setPrix((double) requestParams.get("prix"));
            }
            if (requestParams.containsKey("description")) {
                existingProduct.setDescription((String) requestParams.get("description"));
            }
            if (requestParams.containsKey("quantite")) {
                existingProduct.setQuantite((int) requestParams.get("quantite"));
            }
            if (requestParams.containsKey("categorie")) {
                int categorieId = (int) requestParams.get("categorie");
                // Retrieve the selected category
                Categorie categorie1 = serviceCategorie.selectById(categorieId);

                if (categorie1 != null) {
                    // Set the product's category
                    existingProduct.setCategorie(categorie1);
                } else {
                    // Handle the case where the category with the provided ID doesn't exist
                    // You can show an error message or take appropriate action
                    throw new RuntimeException("Category not found");
                }
            }
            if (requestParams.containsKey("imageUrl")) {
                existingProduct.setImage((String) requestParams.get("imageUrl"));
            }

            // Save the updated product
            service.modifier(existingProduct);
        } else {
            // Handle the case where the product with the provided ID doesn't exist
            // You can show an error message or take appropriate action
            throw new RuntimeException("Product not found");
        }
    }




    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable int id) {
        service.supprimer(id);
    }
}
