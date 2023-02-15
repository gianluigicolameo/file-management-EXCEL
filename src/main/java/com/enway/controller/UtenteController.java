package com.enway.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	@Qualifier("pdfFileServiceImpl")
	private FileService pdfFileService;
	
	@Autowired
	@Qualifier("excelServiceImpl")
	private FileService excelFileService;
	
	@GetMapping("/utenti")
	public ArrayList<Utente> showUtenti() {
		return utenteService.showAllUtenti();
	}
	
	@PostMapping("/utente")
	public ArrayList<Utente> addUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@PutMapping("/utente")
	public ArrayList<Utente> updateUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@DeleteMapping("/utente")
	public ArrayList<Utente> deleteUtente(@RequestParam Integer id){
		utenteService.deleteUtente(id);
		return utenteService.showAllUtenti();
	}
	@GetMapping("/excel")
	public ArrayList<Utente> createExcel(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		excelFileService.writeFile(utenti, path);
		return utenti;
	}
	
	@GetMapping("/pdf")
	public ArrayList<Utente> createPdf(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		pdfFileService.writeFile(utenti, path);
		return utenti;
	}
	
	@PutMapping("/pdf-update")
	public ArrayList<Utente> updatePdf(@RequestParam("path") String path, @RequestParam("textToAdd") String textToAdd){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		pdfFileService.updateFile(utenti, path, textToAdd);
		return utenti;
	}
	
	@PutMapping("/excel-update")
	public ArrayList<Utente> updateExcel(@RequestParam("path") String path, @RequestParam("textToAdd") String textToAdd){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		excelFileService.updateFile(utenti, path, textToAdd);
		return utenti;
	}
	
}
