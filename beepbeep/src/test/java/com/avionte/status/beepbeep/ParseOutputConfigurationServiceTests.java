package com.avionte.status.beepbeep;

import org.junit.Test;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.OutputConfigurationException;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.ParseOutputConfigurationService;

import junit.framework.TestCase;

public class ParseOutputConfigurationServiceTests extends TestCase{
	
	private IParseOutputConfigurationService sut;
	
	public ParseOutputConfigurationServiceTests(String testName) {
		super(testName);
	}
	
	protected void setUp() {
		sut = new ParseOutputConfigurationService();
	}
	
	public void test_createOutputConfiguration_WhenGivenEmptyString_ReturnsNull() throws OutputConfigurationException {
		assertEquals(null, sut.parse(""));
	}
	
	public void test_createOutputConfiguration_WhenParsedInputsLessThan11_ReturnsNull() throws OutputConfigurationException {
		assertEquals(null, sut.parse("1|2|3"));
	}
	
	public void test_createOutputConfiguration_WhenParsedInputsGreaterThan12_ReturnsNull() throws OutputConfigurationException {
		assertEquals(null, sut.parse("1|2|3|4|5|6|7|8|9|10|11|12"));
	}
	
	public void test_createOutputConfiguration_WhenConfigNotEnabled_ReturnsNull() throws OutputConfigurationException {
		assertEquals(null, sut.parse("false|<pin_code>|<base_url>|<request_type>|<response_type>|<response_property>|<response_positive_value>|<fail_on_bad_response>|<username>|<password>|<csv_inserts>"));
	}
	
	public void test_createOutputConfiguration_WhenPinNotFound_ThrowsOutputConfigurationException() throws OutputConfigurationException {
		boolean exceptionThrown = false;
		
		try {
			sut.parse("true|999|<base_url>|<request_type>|<response_type>|<response_property>|<response_positive_value>|<fail_on_bad_response>|<username>|<password>|<csv_inserts>");
		}catch(OutputConfigurationException ex) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	public void test_createOutputConfiguration_WhenRequestTypeCannotBeParsed_ThrowsException() throws OutputConfigurationException {
		boolean exceptionThrown = false;
		
		try {
			sut.parse("true|1|test.com|banana|<response_type>|<response_property>|<response_positive_value>|<fail_on_bad_response>|<username>|<password>|<csv_inserts>");
		}catch(IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	public void test_createOutputConfiguration_WhenResponseTypeCannotBeParsed_ThrowsException() throws OutputConfigurationException {
		boolean exceptionThrown = false;
		
		try {
			sut.parse("true|1|test.com|TEAMCITY|banana|<response_property>|<response_positive_value>|<fail_on_bad_response>|<username>|<password>|<csv_inserts>");
		}catch(IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	public void test_createOutputConfiguration_WhenFailOnExceptionCannotBeParsed_ThrowsException() throws OutputConfigurationException {
		boolean exceptionThrown = false;
		
		try {
			sut.parse("true|1|test.com|TEAMCITY|banana|respProp|respPositiveValue|banana|<username>|<password>|<csv_inserts>");
		}catch(IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	public void test_createOutputConfiguration_WhenSeveralInsertsExist_ReturnsConfigWithSeveralUrls() throws OutputConfigurationException {
		OutputConfiguration config = sut.parse("true|1|test{$}.com|TEAMCITY|XML|respProp|respPositiveValue|true|banana|hunter12|1,2,3");
		
		assertEquals(config.getUrls().length, 3);
	}
}
