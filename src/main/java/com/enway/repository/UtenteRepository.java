package com.enway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enway.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	public List<Utente> findByAdult(Boolean adult);
}
