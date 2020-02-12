
package com.rabo.filevalidator.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.filevalidator.dto.Record;
import com.rabo.filevalidator.enums.FILE_TYPE;
import com.rabo.filevalidator.exceptions.CustomerFileNotFoundException;
import com.rabo.filevalidator.files.CSVFile;
import com.rabo.filevalidator.files.XMLFile;
import com.rabo.filevalidator.operations.FileOperationsFactory;

@RunWith(SpringRunner.class)
public class RaboServiceTest {

	@Mock
	private FileOperationsFactory fileFactory;

	@InjectMocks
	private RaboService raboServiceTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoadAndProcessCSVFile() throws CustomerFileNotFoundException, IOException {
		when(fileFactory.getFileInstance(FILE_TYPE.CSV)).thenReturn(new CSVFile());
		List<Record> actualRecords = raboServiceTest.loadAndProcessCSVFile();

		Assert.assertNotNull(actualRecords);

		verify(fileFactory, times(1)).getFileInstance(FILE_TYPE.CSV);
	}

	@Test
	public void testLoadAndProcessXMLFile() throws CustomerFileNotFoundException {
		when(fileFactory.getFileInstance(FILE_TYPE.XML)).thenReturn(new XMLFile());
		List<Record> actualRecords = raboServiceTest.loadAndProcessXMLFile();

		Assert.assertNotNull(actualRecords);

		verify(fileFactory, times(1)).getFileInstance(FILE_TYPE.XML);
	}

}
