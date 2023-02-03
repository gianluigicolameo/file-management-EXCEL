package com.enway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.repository.UtenteRepository;
import com.enway.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	UtenteRepository utenteRepository;
	
	public void addOrUpdateUtente(Utente utente) {
		utenteRepository.save(utente);
	}
	
	public List<Utente> showAllUtenti() {
		List<Utente> utenti = utenteRepository.findAll();;
		return utenti;
	}
	
	public List<Utente> showByAdult(boolean adult) {
		List<Utente> utentiAdult= utenteRepository.findByAdult(adult);
		return utentiAdult;
	}
	public List<Utente> deleteUtente(Integer id) {		
		utenteRepository.deleteById(id);
		return showAllUtenti();
	}
	
}
