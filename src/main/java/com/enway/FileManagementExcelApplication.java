package com.enway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enway.service.UtenteService;
import com.enway.service.impl.UtenteServiceImpl;

@SpringBootApplication
public class FileManagementExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagementExcelApplication.class, args);
		
	}

}
