package com.avionte.status.beepbeep.core.services.responseProcessors;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.ResponseDataType;

public class XMLResponseProcessor implements IProcessResponse {

	@Override
	public boolean processResponse(OutputConfiguration config, String response) {
		if(config.getResponseType() != ResponseDataType.XML) {
			return false;
		}
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(new ByteArrayInputStream( response.getBytes("UTF-8")));
			
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression xpathExpression = xpath.compile(config.getResponseProperty());
			
			NodeList result = (NodeList) xpathExpression.evaluate(document, XPathConstants.NODESET);
			
			if(result.getLength() > 0) {
				return true;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} 
		
		return false;
	}

}
