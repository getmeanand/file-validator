package com.rabo.filevalidator.operations;

import java.util.EnumMap;
import java.util.Map;

import com.rabo.filevalidator.enums.*;
import com.rabo.filevalidator.files.*;

public abstract class BaseFileOperations {
	protected Map<FILE_TYPE, Object> fileMapObject;

	public BaseFileOperations() {
		super();
		initiateFileTypeMap();
	}

	private void initiateFileTypeMap() {
		fileMapObject = new EnumMap<FILE_TYPE, Object>(FILE_TYPE.class);

		// add some entries
		fileMapObject.put(FILE_TYPE.XML, new XMLFile());
		fileMapObject.put(FILE_TYPE.CSV, new CSVFile());

	}

	public abstract Object getFileInstance(FILE_TYPE fileType);
}
