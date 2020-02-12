package com.rabo.filevalidator.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.controller.RaboController;
import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.enums.FILE_TYPE;
import com.rabo.filevalidator.exceptions.CustomerFileNotFoundException;
import com.rabo.filevalidator.operations.FileOperations;
import com.rabo.filevalidator.operations.FileOperationsFactory;

@Service
public class RaboService {

	private static final Logger logger = LoggerFactory.getLogger(RaboController.class);

	@Autowired
	private FileOperationsFactory fileFactory;

	private List<Record> filteredCustomerList;

	public List<Record> loadAndProcessCSVFile() throws CustomerFileNotFoundException, IOException {
		FileOperations csvFileOperation = (FileOperations) fileFactory.getFileInstance(FILE_TYPE.CSV);

		filteredCustomerList = csvFileOperation
				.readCustomerValidatorFile(csvFileOperation.loadCustomerValidatorFile(RaboConstants.VALIDATE_FILE_CSV));
		logger.info("Failed List CSV Validator File <<>> :" + filteredCustomerList);

		return filteredCustomerList;
	}

	public List<Record> loadAndProcessXMLFile() throws CustomerFileNotFoundException {
		FileOperations xmlFileOperation = (FileOperations) fileFactory.getFileInstance(FILE_TYPE.XML);

		filteredCustomerList = xmlFileOperation
				.readCustomerValidatorFile(xmlFileOperation.loadCustomerValidatorFile(RaboConstants.VALIDATE_FILE_XML));

		logger.info("Failed List XML Validator File <<>> :" + filteredCustomerList);

		return filteredCustomerList;
	}
}
