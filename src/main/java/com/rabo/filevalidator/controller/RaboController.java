package com.rabo.filevalidator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.exceptions.CustomerFileNotFoundException;
import com.rabo.filevalidator.service.RaboService;

@RestController
public class RaboController {

	@Autowired
	RaboService raboService;

	@GetMapping(RaboConstants.CSV_FILE_VALIDATOR_URL)
	public ResponseEntity<List<Record>> processCSVFile() throws CustomerFileNotFoundException, IOException {

		return Optional.ofNullable(raboService.loadAndProcessCSVFile())
				.map(csvCustomerInfo -> new ResponseEntity<>(csvCustomerInfo, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
	}

	@GetMapping(RaboConstants.XML_FILE_VALIDATOR_URL)
	public ResponseEntity<List<Record>> processXMLFile() throws CustomerFileNotFoundException {

		return Optional.ofNullable(raboService.loadAndProcessXMLFile())
				.map(xmlCustomerInfo -> new ResponseEntity<>(xmlCustomerInfo, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
	}

}
