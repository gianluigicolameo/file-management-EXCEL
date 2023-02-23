package com.enway.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.enway.entity.Utente;
import com.enway.service.FileService;

@Service
@Component("txtFileServiceImpl")
public class txtFileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(txtFileServiceImpl.class);

	@Override
	public void writeFile(ArrayList<Utente> utenti, String path) {
		try {
			FileWriter myWriter = new FileWriter(path);
			myWriter.write("Testo prova");
			myWriter.close();
			logger.info("File successfully created and written to.");
		} catch (IOException e) {
			logger.error("An error occurred while creating and writing to the file.");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFile(String path) {

		File file = new File(path);
		if (file.delete()) {
			logger.info("File deleted succesfully");
		} else {
			logger.error("An error occurred during deletion");
		}
	}

	@Override
	public void updateFile(ArrayList<Utente> utenti, String path, String... textToAdd) {
		try {
			String toWrite = textToAdd.toString();
			FileWriter myWriter2 = new FileWriter(path, true);
			myWriter2.write(toWrite);
			myWriter2.close();
			logger.info("File successfully updated.");
		} catch (IOException e) {
			logger.error("An error occurred while updating the file.");
			e.printStackTrace();
		}

	}

	@Override
	public void readFile(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Errore nella lettura del file");
		}
	}

}
