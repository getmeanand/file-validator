package com.rabo.filevalidator.operations;

import java.util.EnumMap;
import java.util.Map;

import com.rabo.filevalidator.enums.FILETYPE;
import com.rabo.filevalidator.files.CSVFile;
import com.rabo.filevalidator.files.XMLFile;

public abstract class BaseFileOperations {
	protected Map<FILETYPE, Object> fileMapObject;

	public BaseFileOperations() {
		super();
		initiateFileTypeMap();
	}

	private void initiateFileTypeMap() {
		fileMapObject = new EnumMap<FILETYPE, Object>(FILETYPE.class);

		// add some entries
		fileMapObject.put(FILETYPE.XML, new XMLFile());
		fileMapObject.put(FILETYPE.CSV, new CSVFile());

	}

	public abstract Object getFileInstance(FILETYPE fileType);
}
