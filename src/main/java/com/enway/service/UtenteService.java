package com.enway.service;

import java.util.ArrayList;
import java.util.List;

import com.enway.entity.Utente;

public interface UtenteService {
	public void addOrUpdateUtente(Utente utente);
	public List<Utente> showByAdult(boolean adult);
	public ArrayList<Utente> showAllUtenti();
	public List<Utente> deleteUtente(Integer id);
}
