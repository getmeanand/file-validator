package com.rabo.filevalidator.operations;

import org.springframework.stereotype.Component;

import com.rabo.filevalidator.enums.FILETYPE;

@Component
public class FileOperationsFactory extends BaseFileOperations {
	/**
	 * this function accepts the customer input file types ie xml, csv and return
	 * the corresponding instances accordingly from the fileMapObject. this is the
	 * implementation of factory pattern method
	 * 
	 * @param strFileFile
	 * 
	 */
	public Object getFileInstance(FILETYPE fileTypeObj) {
		if (fileTypeObj == null) {
			return null;
		}
		if (FILETYPE.XML.equals(fileTypeObj)) { // checks if files type is xml
			return fileMapObject.get(FILETYPE.XML);
		} else if (FILETYPE.CSV.equals(fileTypeObj)) {// checks if files type is csv
			return  fileMapObject.get(FILETYPE.CSV);
		}

		return null;
	}

}
