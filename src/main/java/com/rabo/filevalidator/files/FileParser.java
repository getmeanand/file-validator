package com.rabo.filevalidator.files;

import java.util.List;

import com.rabo.filevalidator.dto.Record;

public abstract class FileParser {
	public List<Record> parseCustomerInformation(List<String> listLines) {
		return null;
	};

	public abstract List<Record> validateCustomerDataList(List<Record> customerFileList);
}
