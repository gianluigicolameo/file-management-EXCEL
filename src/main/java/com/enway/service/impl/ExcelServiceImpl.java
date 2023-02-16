package com.enway.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.enway.entity.Utente;
import com.enway.service.FileService;

@Component("excelServiceImpl")
public class ExcelServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(PdfFileServiceImpl.class);

	public void writeFile(ArrayList<Utente> utenti, String path) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet("First Try");
			XSSFRow row = null;

			row = sheet.createRow(0);
			Cell cell0 = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);

			cell0.setCellValue("Prova");
			cell1.setCellValue("Numero");
			cell2.setCellValue("Uno");

			/*
			 * List<String> names = utenti.stream().map(utente ->
			 * utente.getFirstName()).forEach(d -> { String s = ""; if(s.equals(cell2))
			 * });.collect(Collectors.toList());
			 */

			for (int i = 0; i < utenti.size(); i++) {
				row = sheet.createRow(i + 1);
				for (int j = 0; j < 3; j++) {
					Cell cell = row.createCell(j);
					if (j == 0) {
						cell.setCellValue(utenti.get(i).getFirstName());
					} else if (j == 1) {
						cell.setCellValue(utenti.get(i).getLastName());
					} else if (j == 2) {
						cell.setCellValue(utenti.get(i).getAge());
					}
				}
			}

			FileOutputStream fos = new FileOutputStream(path);

			workbook.write(fos);
			fos.close();
			workbook.close();
			logger.info("Excel creato");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			logger.error("Errore nella creazione del file");
		} catch (IOException e) {

			e.printStackTrace();
			logger.error("Errore nella scrittura su workbook");
		}

	}

	@Override
	public void updateFile(ArrayList<Utente> utenti, String path, String... textToAdd) {
		try {
			FileInputStream inputStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			XSSFSheet sheet = workbook.getSheet("First Try");
			if (sheet == null) {
				sheet = workbook.createSheet("First Try");
			}

			// crea un set per tenere traccia degli elementi già presenti nel file Excel
			ArrayList<Utente> elementiPresenti = new ArrayList<>();
			int rowCount = sheet.getLastRowNum();
			for (int i = 1; i <= rowCount; i++) {
				XSSFRow row = sheet.getRow(i);
				Utente utente = new Utente();
				for (int j = 0; j < 3; j++) {
					Cell cell = row.getCell(j);
					if (j == 0) {
						utente.setFirstName(cell.getStringCellValue());
					} else if (j == 1) {
						utente.setLastName(cell.getStringCellValue());
					} else if (j == 2) {
						utente.setAge((int) cell.getNumericCellValue());
					}
				}
				elementiPresenti.add(utente);
			}

            ArrayList<Utente> utentiDaAggiungere = new ArrayList<>();
            
            //CONTAINS
            //STRING POOL
            //EQUALS
            //EQUALE IGNORE CASE
            for(Utente utente : utenti) {
            	for(Utente utentePresente : elementiPresenti) {
            		if(utentePresente.getFirstName()!= utente.getFirstName() || 
            			utentePresente.getLastName()!= utente.getLastName() ||
            			utentePresente.getAge()!=utente.getAge()) {
            			utentiDaAggiungere.add(utente);
            		}
            	}
            }
            

			for (Utente utenteDaAggiungere : utentiDaAggiungere) {
				XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
				for (int j = 0; j < 3; j++) {
					Cell cell = row.createCell(j);
					if (j == 0) {
						cell.setCellValue(utenteDaAggiungere.getFirstName());
					} else if (j == 1) {
						cell.setCellValue(utenteDaAggiungere.getLastName());
					} else if (j == 2) {
						cell.setCellValue(utenteDaAggiungere.getAge());
					}
				}

			}

			// salva le modifiche nel file Excel
			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			logger.info("Excel modificato");
		} catch (FileNotFoundException e) {

			e.printStackTrace();

			logger.error("Errore nella modifica del file {}");			
		} catch (IOException e) {

			e.printStackTrace();
			logger.error("Errore nella scrittura su workbook");
		}

	}

	@Override
	public void deleteFile(String path) {
		File file = new File(path);

		if (file.exists()) {
			file.delete();
			logger.info("Excel eliminato");
		} else {
			logger.error("L'excel non esiste");
		}
	}

	@Override
	public void readFile(String path) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				for (Cell cell : row) {
					System.out.print(cell.toString());
				}
				System.out.println();
			}
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Errore nella lettura del file");
		}
	}
}