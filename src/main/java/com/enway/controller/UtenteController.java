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
	
	@Autowired
	@Qualifier("txtFileServiceImpl")
	private FileService txtFileService;
	
	@GetMapping("/utenti")
	public ArrayList<Utente> showUtenti() {
		return utenteService.showAllUtenti();
	}
	
	@PostMapping("/utente-add")
	public ArrayList<Utente> addUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@PutMapping("/utente-update")
	public ArrayList<Utente> updateUtente(@RequestBody Utente utente){
		utenteService.addOrUpdateUtente(utente);
		return utenteService.showAllUtenti();
	}
	@DeleteMapping("/utente-delete")
	public ArrayList<Utente> deleteUtente(@RequestParam Integer id){
		utenteService.deleteUtente(id);
		return utenteService.showAllUtenti();
	}
	@GetMapping("/excel-create")
	public ArrayList<Utente> createExcel(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		excelFileService.writeFile(utenti, path);
		return utenti;
	}
	
	@GetMapping("/pdf-create")
	public ArrayList<Utente> createPdf(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		pdfFileService.writeFile(utenti, path);
		return utenti;
	}
	
	@GetMapping("/txt-create")
	public ArrayList<Utente> createTxt(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		txtFileService.writeFile(utenti, path);
		return utenti;
	}

		@PutMapping("/excel-update")
	public ArrayList<Utente> updateExcel(@RequestParam("path") String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		excelFileService.updateFile(utenti, path);
		return utenti;
	}
	
	@PutMapping("/pdf-update")
	public ArrayList<Utente> updatePdf(@RequestParam("path") String path, @RequestParam("textToAdd") String textToAdd){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		pdfFileService.updateFile(utenti, path, textToAdd);
		return utenti;
	}
	
	@PutMapping("/txt-update")
	public ArrayList<Utente> updateTxt(@RequestParam("path") String path, @RequestParam("textToAdd") String textToAdd){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		txtFileService.updateFile(utenti, path, textToAdd);
		return utenti;
	}

	@GetMapping("/excel-read")
	public ArrayList<Utente> readExcel(@RequestParam String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		excelFileService.readFile(path);
		return utenti;
	}
	
	@GetMapping("/pdf-read")
	public ArrayList<Utente> readPdf(@RequestParam("path") String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		pdfFileService.readFile(path);
		return utenti;
	}
	
	@GetMapping("/txt-read")
	public ArrayList<Utente> readTxt(@RequestParam("path") String path){
		ArrayList<Utente> utenti = utenteService.showAllUtenti();
		txtFileService.readFile(path);
		return utenti;
	}
	
	@DeleteMapping("/excel-delete")
	public void deleteExcel(@RequestParam String path){
		excelFileService.deleteFile(path);		
	}
	
	@DeleteMapping("/pdf-delete")
	public void deletePdf(@RequestParam String path){
		pdfFileService.deleteFile(path);		
	}
	
	@DeleteMapping("/txt-delete")
	public void deleteTxt(@RequestParam String path){
		txtFileService.deleteFile(path);		
	}
}
