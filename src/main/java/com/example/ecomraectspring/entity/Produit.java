package com.example.ecomraectspring.entity;




import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private String image;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_c")
    private Categorie categorie;

    public Produit(String nom, double prix, int quantite, String image, String description, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.description = description;
        this.categorie = categorie;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
