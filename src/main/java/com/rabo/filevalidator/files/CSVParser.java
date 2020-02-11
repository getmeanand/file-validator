package com.rabo.filevalidator.files;

import java.util.ArrayList;
import java.util.List;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.utils.RaboUtils;

public class CSVParser extends FileParser {

	RaboUtils singletonRaboUtils = RaboUtils.getInstance();

	/**
	 * this function accepts the list of validate file lines and split by comma(,)
	 * delimiter and parse and return the parsed info to the caller function
	 * 
	 * @param paramList
	 * @return
	 */
	@Override
	public List<Record> parseCustomerInformation(List<String> listLines) {
		List<Record> returnList = new ArrayList<>();

		listLines.forEach(data -> {
			String[] strCustomerDetails = data.split(RaboConstants.COMMA_DELIMITER);
			if (strCustomerDetails != null) {
				returnList.add(parseSplitedValues(strCustomerDetails));
			}
		});
		
		
		return returnList;

	}

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

	/**
	 * this function accepts the array of string values and assign it into record
	 * object and return the record object to the caller function
	 * 
	 * @param paramList
	 * @return
	 */
	public Record parseSplitedValues(String[] splitRecDetails) {
		Record record = null;
		if (!nullCheck(splitRecDetails)) {
			int index = RaboConstants.INT_VAL_ZERO;
			if (splitRecDetails != null) {
				record = new Record();
				record.setReference(splitRecDetails[index++]);
				record.setAccountNumber(splitRecDetails[index++]);
				record.setDescription(splitRecDetails[index++]);
				record.setStartBalance(Double.parseDouble(splitRecDetails[index++]));
				record.setMutation(Double.parseDouble(splitRecDetails[index++]));
				record.setEndBalance(Double.parseDouble(splitRecDetails[index++]));
			}
		}
		return record;
	}

	/**
	 * this function accepts the string of array and check weather the elements are
	 * null or not
	 * 
	 * @param array
	 * @return true or false
	 */
	public boolean nullCheck(String[] value) {
		if (value == null) {
			return true;
		}
		if (value.length == RaboConstants.INT_VAL_ZERO) {
			return true;
		}
		for (String strElement : value) {
			if (strElement == null) {
				return true;
			}
			if (strElement.length() == RaboConstants.INT_VAL_ZERO) {
				return true;
			}
			if (strElement.isEmpty()) {
				return true;
			}
		}
		return false;

	}

}
