package com.rabo.filevalidator.files;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.dto.Records;
import com.rabo.filevalidator.operations.FileOperations;
import com.rabo.filevalidator.utils.RaboUtils;

@Component
public class XMLFile extends FileOperations {
	private static final Logger logger = LoggerFactory.getLogger(XMLFile.class);
	File xmlCustomerFile;
	private List<Record> customerFileDataList = null;

	RaboUtils singletonRaboUtils = RaboUtils.getInstance();

	/**
	 * This function will read the validator file of xml type and do validate the
	 * customer records based on the condition and return the contents as records
	 * 
	 * @param xmlFile
	 * @return returnList
	 */
	@Override
	public List<Record> readCustomerValidatorFile(File xmlFile) {
		XMLParser xmlParser = new XMLParser();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			Records record = (Records) unMarshaller.unmarshal(xmlFile);
			customerFileDataList = record.getRecord();
			if (customerFileDataList != null && customerFileDataList.size() != RaboConstants.INT_VAL_ZERO) {
				customerFileDataList=	xmlParser.validateCustomerDataList(customerFileDataList);
			}

		} catch (JAXBException ex) {
			logger.error("Read Xml File getting erron on " + ex.getMessage());
		}
		return customerFileDataList;
	}

	/**
	 * This function load the customer record details file from the class path and
	 * will return the caller function
	 * 
	 * @param xmlFilePath
	 * @return xmlCustomerFile
	 */
	@Override
	public File loadCustomerValidatorFile(String xmlFilePath) {
		xmlCustomerFile = singletonRaboUtils.loadFilesForValidate(xmlFilePath);
		return xmlCustomerFile;
	}

}
