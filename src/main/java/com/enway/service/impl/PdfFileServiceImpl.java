package com.enway.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

@Service
@Component("pdfFileServiceImpl")
public class PdfFileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(PdfFileServiceImpl.class);

	public void writeFile(ArrayList<Utente> utenti, String path) {

		try {

			Document document = new Document();

			OutputStream outputStream = new FileOutputStream(new File("C:/Users/n.laperna/Desktop/TestFile.pdf"));

			PdfWriter.getInstance(document, outputStream);

			document.open();
			
			document.addTitle("Lista studenti");
			
			// List orderedList = new List(List.ORDERED);
			
			List orderedList = new List(List.ORDERED);
			
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
			logger.error("Errore nella creazione del file.");
		}
	}


	@Override
	public void updateFile(ArrayList<Utente> utenti, String path, String textToAdd) {

		// TODO Auto-generated method stub
		try {
			PdfReader pdfReader = new PdfReader(path);
						
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:/Users/n.laperna/Desktop/TestFile.pdf"));
						
			PdfContentByte canvas = pdfStamper.getOverContent(1);
			
			BaseFont font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
			
			canvas.beginText();
			
			canvas.setFontAndSize(font, 12);
			
			canvas.showTextAligned(Element.ALIGN_CENTER, textToAdd, 300, 500, 0);
			
			canvas.endText();	
			
			pdfStamper.close();
			
			pdfReader.close();

			
			logger.info("Pdf modificato con successo");
		} catch (Exception e) {
			logger.error("Errore nella modifica del file.");
		}
		
	}

	@Override
	public void deleteFile(String path) {
		// TODO Auto-generated method stub
		File file = new File(path);

		if (file.exists()) {
			file.delete();
			logger.info("Pdf eliminato");
		} else {
			logger.info("Il pdf non esiste");
		}
	}


	@Override
	public void readFile() {
		// TODO Auto-generated method stub
		
	}

}
