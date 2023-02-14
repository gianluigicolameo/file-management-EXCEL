package com.enway.service;

import java.util.ArrayList;

import com.enway.entity.Utente;

public interface UtenteService {
	public void addOrUpdateUtente(Utente utente);
	public ArrayList<Utente> showByAdult(boolean adult);
	public ArrayList<Utente> showAllUtenti();
	public ArrayList<Utente> deleteUtente(Integer id);
}
