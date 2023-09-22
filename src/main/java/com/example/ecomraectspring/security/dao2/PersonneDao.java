package com.example.ecomraectspring.security.dao2;


import com.example.ecomraectspring.security.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneDao extends JpaRepository<Personne, Integer> {

	
	/*public Personne findByNomAndPrenom(String nom);
	
	@Query("select p from Personne p where p.nom=:n")
	public Personne chercherParNom(@Param("n") String nom);*/
	
	
	
}
