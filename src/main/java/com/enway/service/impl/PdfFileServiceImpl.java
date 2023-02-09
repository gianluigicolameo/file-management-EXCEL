package com.enway.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfFileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(PdfFileServiceImpl.class);

	public void writePdf(List<Utente> utenti) {

		try {

			Document document = new Document();

			OutputStream outputStream = new FileOutputStream(new File("C:/Users/n.laperna/Desktop/TestFile.pdf"));

			PdfWriter.getInstance(document, outputStream);

			document.open();
			
			document.addTitle("Lista studenti");
			
			com.itextpdf.text.List orderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
			
			for(int i=0; i< utenti.size(); i++) {
				orderedList.add(new com.itextpdf.text.ListItem(utenti.get(i).toString()));
			}
			
			document.add(orderedList);

			document.close();
			outputStream.close();

			logger.info("Pdf creato con successo");

		} catch (Exception e) {
			logger.warn("Errore nella creazione del file.");
		}
	}

}
