package com.enway.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.enway.entity.Utente;
import com.enway.service.UtenteService;


public class ExcelServiceImpl implements UtenteService{

	@Override
	public void addOrUpdateUtente(Utente utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utente> showByAdult(boolean adult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> showAllUtenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> deleteUtente(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void writeXlsx(List<Utente> utenti) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			FileOutputStream fileOut = new FileOutputStream("file.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}

