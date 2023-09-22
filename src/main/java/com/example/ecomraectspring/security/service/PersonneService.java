package com.example.ecomraectspring.security.service;


import com.example.ecomraectspring.security.dao2.PersonneDao;
import com.example.ecomraectspring.security.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService implements IpersersonneService {
	
	@Autowired
	PersonneDao dao;
	
	
	public Personne ajouter(Personne p)
	{
		Personne p2= dao.save(p);
		return p2;
	}
	
	public Personne selecOne(int id)
	{
		return dao.findById(id).get();
	}
	
	public List<Personne> selectAll()
	{
		return dao.findAll();
	}
	
	public void supprimer(int id)
	{
		dao.deleteById(id);
	}
	
	public void modifier(Personne p)
	{
		dao.save(p);
	}

}
