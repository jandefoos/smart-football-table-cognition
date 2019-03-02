package de.fiduciagad.de.sft.test;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class OpenCVHandlerTest {

	@Test
	public void readSimpleConsole() {
		
		OpenCVHandler opencv = new OpenCVHandler();
		
		opencv.startPythonModule("test.py");
		
		List<String> output = opencv.listenToOutput();
		
		assertThat(output.size(), CoreMatchers.is(9));
		assertThat(output.get(0), CoreMatchers.is("1"));
		
	}
	

}