package com.rabo.filevalidator.files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.controller.RaboController;
import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.operations.FileOperations;
import com.rabo.filevalidator.utils.RaboUtils;

@Component
public class CSVFile extends FileOperations {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RaboController.class);
	RaboUtils singletonRaboUtils = RaboUtils.getInstance();

	private List<Record> customerDataList = null;

	/**
	 * This function will read the validator file of csv type and do validate the
	 * customer records based on the condition and return the contents as records
	 * 
	 * @param paramFile
	 * @return
	 */
	@Override
	public List<Record> readCustomerValidatorFile(File csvFile) {
		CSVParser csvParser = new CSVParser();
		// fileLineStream will be auto closed since used with try with resources
		try (Stream<String> fileLineStream = Files.lines(Paths.get(csvFile.toString()))) {
			customerDataList = new ArrayList<>();
			List<String> csvFileInformationList = fileLineStream.skip(RaboConstants.INT_VAL_ONE)
					.collect(Collectors.toList());

			if (csvFileInformationList != null && csvFileInformationList.size() != RaboConstants.INT_VAL_ZERO) {
				customerDataList = csvParser
						.validateCustomerDataList(csvParser.parseCustomerInformation(csvFileInformationList));
			}

		} catch (Exception ex) {
			logger.error("readCsvFile getting erron on " + ex.getMessage());
		}
		return customerDataList;
	}

	/**
	 * This function load the customer record details file from the class path and
	 * will return the caller function
	 * 
	 * @param csvFilePath
	 * @return
	 */
	@Override
	public File loadCustomerValidatorFile(String csvFilePath) {
		File file = singletonRaboUtils.loadFilesForValidate(csvFilePath);
		return file;
	}

}
