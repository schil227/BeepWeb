package com.avionte.status.beepbeep;

import java.io.IOException;

import com.avionte.status.beepbeep.core.data.OutputConfigurationException;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.ParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.PopulateOutputConfigurationService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParseOutputConfigurationServiceTests extends TestCase{
	
	private IParseOutputConfigurationService sut;
	
	public ParseOutputConfigurationServiceTests(String testName) {
		super(testName);
	}
	
	protected void setUp() {
		sut = new ParseOutputConfigurationService();
	}
	
	public static Test suite() {
		return new TestSuite(ParseOutputConfigurationServiceTests.class);
	}
	
	public void test_createOutputConfiguration_WhenGivenEmptyString_ReturnsNull() throws IOException, OutputConfigurationException {
		assertEquals(null, sut.parse(""));
	}
	
	public void test_createOutputConfiguration_WhenParsedInputsLessThan11_ReturnsNull() throws IOException, OutputConfigurationException {
		assertEquals(null, sut.parse("1|2|3"));
	}
	
	public void test_createOutputConfiguration_WhenParsedInputsGreaterThan12_ReturnsNull() throws IOException, OutputConfigurationException {
		assertEquals(null, sut.parse("1|2|3|4|5|6|7|8|9|10|11|12"));
	}
	
	
	public void test_createOutputConfiguration_WhenConfigNotEnabled_ReturnsNull() throws IOException, OutputConfigurationException {
		assertEquals(null, sut.parse("false|<pin_code>|<base_url>|<request_type>|<response_type>|<response_property>|<response_positive_value>|<fail_on_bad_response>|<username>|<password>|<csv_inserts>"));
	}
}
