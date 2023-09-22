package com.example.ecomraectspring.security.service;



import com.example.ecomraectspring.security.entity.Personne;

import java.util.List;

public interface IpersersonneService {
	
	
	public Personne ajouter(Personne p);
	
	public Personne selecOne(int id);
	
	public List<Personne> selectAll();
	
	public void supprimer(int id);
	
	public void modifier(Personne p);

}
