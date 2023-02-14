package com.enway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.enway.service.UtenteService;

@RestController
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/utenti")
	public List<Utente> showUtenti() {
		return utenteService.showAllUtenti();
	}
	
	@PostMapping("/utente")
	public List<Utente> addUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@PutMapping("/utente")
	public List<Utente> updateUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@DeleteMapping("/utente")
	public List<Utente> deleteUtente(@RequestParam Integer id){
		utenteService.deleteUtente(id);
		return utenteService.showAllUtenti();
	}
	@GetMapping("/excel")
	public ArrayList<Utente> createExcel(){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		fileService.writeFile(utenti);
		return utenti;
	}
	
	@GetMapping("/pdf")
	public ArrayList<Utente> createPdf(){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		fileService.writeFile(utenti);
		return utenti;
	}
	
	@PutMapping("/pdf-update")
	public ArrayList<Utente> updatePdf(@RequestBody String path, @RequestBody String textToAdd){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		fileService.updateFile(utenti, path, textToAdd);
		return utenti;
	}
	
}
