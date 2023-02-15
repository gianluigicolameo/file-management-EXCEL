package com.enway.service;

import java.util.ArrayList;


import com.enway.entity.Utente;

public interface FileService {
	
	public void writeFile(ArrayList<Utente> utenti, String path);

	public void deleteFile(String path);
	
	public void updateFile(ArrayList<Utente> utenti, String path, String textToAdd);

	
}
