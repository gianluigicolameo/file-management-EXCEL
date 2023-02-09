package com.enway.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.repository.UtenteRepository;
import com.enway.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	UtenteRepository utenteRepository;
	private static final Logger logger = LoggerFactory.getLogger(UtenteServiceImpl.class);
	
	public void addOrUpdateUtente(Utente utente) {
		utenteRepository.save(utente);
	}
	
	public List<Utente> showAllUtenti() {
		List<Utente> utenti = utenteRepository.findAll();
		return utenti;
	}
	
	public List<Utente> showByAdult(boolean adult) {
		List<Utente> utentiAdult= utenteRepository.findByAdult(adult);
		return utentiAdult;
	}
	public List<Utente> deleteUtente(Integer id) {		
		utenteRepository.deleteById(id);
		return showAllUtenti();
	}

	@Override
	public void writeXlsx(List<Utente> utenti) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("First Try");
		XSSFRow row;
		
		row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		Cell cell1 = row.createCell(1);
		Cell cell2 = row.createCell(2);
		
		cell0.setCellValue("Nome");
		cell1.setCellValue("Cognome");
		cell2.setCellValue("Età");
		
		
		for(int i=0; i< utenti.size(); i++) {
			row= sheet.createRow(i+1);
			for(int j=0; j<3;j++) {
				Cell cell = row.createCell(j);
				if(j==0) {
					cell.setCellValue(utenti.get(i).getFirstName());
				}else if(j==1) {
					cell.setCellValue(utenti.get(i).getLastName());
				}else if(j==2) {
					cell.setCellValue(utenti.get(i).getAge());
				}
			}
			
			
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("C:/Users/g.colameo/Desktop/Prova.xlsx");
			workbook.write(fos);
			fos.close();
			workbook.close();
			logger.info("Excel creato");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("Errore nella creazione de file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("Errore nella scrittura workbook.");
		} 
		
	}
	
}
