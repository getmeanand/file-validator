
package com.rabo.filevalidator;

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
import com.rabo.filevalidator.enums.FILETYPE;
import com.rabo.filevalidator.exceptions.CustomerFileNotFoundException;
import com.rabo.filevalidator.files.CSVFile;
import com.rabo.filevalidator.files.XMLFile;
import com.rabo.filevalidator.operations.FileOperationsFactory;
import com.rabo.filevalidator.service.RaboService;

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
		when(fileFactory.getFileInstance(FILETYPE.CSV)).thenReturn(new CSVFile());
		List<Record> actualRecords = raboServiceTest.loadAndProcessCSVFile();

		Assert.assertNotNull(actualRecords);

		verify(fileFactory, times(1)).getFileInstance(FILETYPE.CSV);
	}

	@Test
	public void testLoadAndProcessXMLFile() throws CustomerFileNotFoundException {
		when(fileFactory.getFileInstance(FILETYPE.XML)).thenReturn(new XMLFile());
		List<Record> actualRecords = raboServiceTest.loadAndProcessXMLFile();

		Assert.assertNotNull(actualRecords);

		verify(fileFactory, times(1)).getFileInstance(FILETYPE.XML);
	}

}
