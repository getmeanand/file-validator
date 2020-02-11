package com.rabo.filevalidator.files;

import java.util.List;

import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.utils.RaboUtils;

public class XMLParser extends FileParser {
	RaboUtils singletonRaboUtils = RaboUtils.getInstance();

	/**
	 * This function accepts the list of customer details list and do validate by
	 * calling validateCustomerRecords method and finally return the failed record
	 * list as response
	 * 
	 * @param customerFileList
	 * 
	 */
	@Override
	public List<Record> validateCustomerDataList(List<Record> customerFileList) {
		return singletonRaboUtils.validateCustomerRecords(customerFileList);
	}

}
