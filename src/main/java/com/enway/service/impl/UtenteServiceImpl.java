package com.enway.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	public ArrayList<Utente> showAllUtenti() {
		ArrayList<Utente> utenti = (ArrayList<Utente>) utenteRepository.findAll();
		return utenti;
	}
	
	public ArrayList<Utente> showByAdult(boolean adult) {
		List<Utente> utentiAdult= utenteRepository.findByAdult(adult);
		return (ArrayList<Utente>) utentiAdult;
	}
	public ArrayList<Utente> deleteUtente(Integer id) {		
		utenteRepository.deleteById(id);
		return showAllUtenti();
	}
	
}
