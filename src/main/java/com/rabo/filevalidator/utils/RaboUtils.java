package com.rabo.filevalidator.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.filevalidator.constants.RaboConstants;
import com.rabo.filevalidator.dto.Record;

@Component
public class RaboUtils {

	private static final Logger logger = LoggerFactory.getLogger(RaboUtils.class);

	/**
	 * Singleton Instance
	 */
	private static RaboUtils raboUtils;

	/**
	 * Create private constructor
	 */
	private RaboUtils() {

	}

	/**
	 * Create a static method to get instance.
	 */
	public static RaboUtils getInstance() {
		if (raboUtils == null) {
			raboUtils = new RaboUtils();
		}
		return raboUtils;

	}

	/**
	 * this function is used to load the customers validator file from the classpath
	 * for validate and send back the qualified file path
	 * 
	 * @param paramFileName
	 * @return
	 */
	public File loadFilesForValidate(String paramFileName) {
		File loadFile = null;
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			loadFile = new File(classLoader.getResource(paramFileName).getFile());
		} catch (Exception ex) {
			logger.error("Read Content File getting erron on " + ex.getMessage());
		}
		return loadFile;
	}

	/**
	 * this function used to validate the customers file contents based on the
	 * condition ie, reference column shouldbe unique values and endBalancce should
	 * only double types *
	 * 
	 * @param paramList
	 * @return
	 */
	public List<Record> validateCustomerRecords(List<Record> paramList) {
		List<Record> validatedCusDataList = new ArrayList<>();
		Set<String> refSet = new HashSet<>();
		try {
			paramList.forEach(paramValue -> {
				if (!refSet.add(paramValue.getReference())) {
					validatedCusDataList.add(paramValue);
				} else {
					double endBalance = Double.valueOf(RaboConstants.DECIMAL_FORMAT
							.format(paramValue.getStartBalance() + paramValue.getMutation()));
					if (endBalance != paramValue.getEndBalance())
						validatedCusDataList.add(paramValue);
				}
			});

		} catch (Exception ex) {
			logger.error("ReportValidation getting erron on " + ex.getMessage());
		}
		return validatedCusDataList;
	}

}
