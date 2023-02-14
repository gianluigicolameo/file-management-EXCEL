package com.enway.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfFileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(PdfFileServiceImpl.class);

	public void writeFile(ArrayList<Utente> utenti) {

		try {

			Document document = new Document();

			OutputStream outputStream = new FileOutputStream(new File("C:/Users/n.laperna/Desktop/TestFile.pdf"));

			PdfWriter.getInstance(document, outputStream);

			document.open();
			
			document.addTitle("Lista studenti");
			
			// List orderedList = new List(List.ORDERED);
			
			com.itextpdf.text.List orderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
			
			for(int i=0; i< utenti.size(); i++) {
				orderedList.add(new ListItem(utenti.get(i).toString()));
			}
			
			document.add(orderedList);
			
            Image image = Image.getInstance("C:/Users/n.laperna/Downloads/image.png");
            // Set the position of the image.
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(70, 70);
 
            // Adding image to the document
            document.add(image);

			document.close();
			outputStream.close();

			logger.info("Pdf creato con successo");

		} catch (Exception e) {
			logger.warn("Errore nella creazione del file.");
		}
	}

	@Override
	public void updateFile(ArrayList<Utente> utenti) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile() {
		// TODO Auto-generated method stub
		
	}

}
