package com.enway.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@Service
@Component("pdfFileServiceImpl")
public class PdfFileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(PdfFileServiceImpl.class);

	public void writeFile(ArrayList<Utente> utenti, String path) {

		try {

			Document document = new Document();

			OutputStream outputStream = new FileOutputStream(new File(path));

			PdfWriter.getInstance(document, outputStream);

			document.open();

			document.addTitle("Lista studenti");

			// List orderedList = new List(List.ORDERED);

			List orderedList = new List(List.ORDERED);

			for (int i = 0; i < utenti.size(); i++) {
				orderedList.add(new ListItem(utenti.get(i).toString()));
			}

			document.add(orderedList);

			document.close();

			outputStream.close();

			logger.info("Pdf creato con successo");

		} catch (Exception e) {
			logger.error("Errore nella creazione del file.");
		}
	}

	@Override
	public void updateFile(ArrayList<Utente> utenti, String path, String... textToAdd) {

		// TODO Auto-generated method stub
		String inputFile = "input.pdf";

        PdfReader reader;
		try {
			reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(inputFile));
	        PdfContentByte canvas = stamper.getOverContent(1);

	        canvas.saveState();
	        canvas.beginText();
	        canvas.setFontAndSize(BaseFont.createFont(), 12);
	        canvas.setTextMatrix(100, 100);
	        canvas.showText("Hello World");
	        canvas.endText();
	        canvas.restoreState();

	        stamper.close();
	        reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void readFile(String path) {
		try {
			// Create PdfReader instance.
			PdfReader pdfReader = new PdfReader(path);

			// Get the number of pages in pdf.
			int pages = pdfReader.getNumberOfPages();

			// Iterate the pdf through pages.
			for (int i = 1; i <= pages; i++) {
				// Extract the page content using PdfTextExtractor.
				String pageContent = PdfTextExtractor.getTextFromPage(pdfReader, i);

				// Print the page content on console.
				System.out.println("Users" + i + ": " + pageContent);
			}

			// Close the PdfReader.
			pdfReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Errore nella lettura del file");
		}

	}

}
