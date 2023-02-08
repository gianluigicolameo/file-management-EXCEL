package com.enway.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfFileServiceImpl implements FileService {

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
			
            Image image = Image.getInstance("C:/Users/n.laperna/Downloads/image.png");
            // Set the position of the image.
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(70, 70);
 
            // Adding image to the document
            document.add(image);

			document.close();
			outputStream.close();

			System.out.println("Pdf created successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
