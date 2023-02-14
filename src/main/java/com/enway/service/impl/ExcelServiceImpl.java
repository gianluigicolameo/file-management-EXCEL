package com.enway.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;

@Service
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
			
			/*List<String> names = utenti.stream().map(utente -> utente.getFirstName()).forEach(d -> {
				String s = "";
				if(s.equals(cell2))
			});.collect(Collectors.toList());*/
			
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

			FileOutputStream fos = new FileOutputStream(path);
			
			workbook.write(fos);
			fos.close();
			workbook.close();
			logger.info("Excel creato");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			logger.error("Errore nella creazione del file {}");			
		} catch (IOException e) {

			e.printStackTrace();
			logger.error("Errore nella scrittura su workbook");
		}
		
     }

	@Override
	public void updateFile(ArrayList<Utente> utenti, String path) {
		try {
			FileInputStream inputStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workbook.createSheet("Second Try");
			XSSFRow row = null;
			
			row = sheet.createRow(0);
			Cell cell0 = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);
			
			cell0.setCellValue("Prova");
			cell1.setCellValue("Numero");
			cell2.setCellValue("Uno");
			
			/*List<String> names = utenti.stream().map(utente -> utente.getFirstName()).forEach(d -> {
				String s = "";
				if(s.equals(cell2))
			});.collect(Collectors.toList());*/
			
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

			FileOutputStream fos = new FileOutputStream(path);
			
			workbook.write(fos);
			fos.close();
			workbook.close();
			logger.info("Excel creato");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			logger.error("Errore nella creazione del file {}");			
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
        	logger.info("L'excel non esiste");
        }
		
	}
}