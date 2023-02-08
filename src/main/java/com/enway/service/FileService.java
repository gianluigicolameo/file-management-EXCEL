package com.enway.service;

import java.util.List;

import com.enway.entity.Utente;

public interface FileService {
	
	public void writePdf(List<Utente> utenti);

}
