package com.enway.service;

import java.util.ArrayList;

import com.enway.entity.Utente;

public interface FileService {
	
	public void createFile();
	
	public void writeFile(ArrayList<Utente> utenti);
	
	public void updateFile(ArrayList<Utente> utenti);
	
	public void deleteFile();
	
}
