package com.rabo.filevalidator.operations;

import java.io.File;
import java.util.List;

import com.rabo.filevalidator.dto.Record;

public abstract class FileOperations {
	public abstract List<Record> readCustomerValidatorFile(File file);

	public abstract File loadCustomerValidatorFile(String strFile);

}
