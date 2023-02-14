package com.enway.service;

import java.util.ArrayList;

import com.enway.entity.Utente;

public interface FileService {
	
	public void writeFile(ArrayList<Utente> utenti, String path);
	
	public void updateFile(ArrayList<Utente> utenti, String path);
	
<<<<<<< HEAD
	public void deleteFile(String path);
=======
	public void updateFile(ArrayList<Utente> utenti, String path, String textToAdd);
	
	public void deleteFile();
>>>>>>> ddf3a29086ed0dfc4f08803d29700f7e7a52de6d
	
}
