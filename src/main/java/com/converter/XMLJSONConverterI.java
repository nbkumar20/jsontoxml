package com.converter;

import java.io.IOException;

import com.util.XmlConversionException;

/**
 * This interface provides methods that are required for creating a converter from json to xml.
 */
public interface XMLJSONConverterI {
	
	/**
	   * Method that reads json from given file, converts it to xml and output the xml data
	   * to the given file
	   *
	   * @param jsonFile
	   * @param xmlFile
	   * 
	   * @throws XmlConversionException 
	   */
	  public void convertJsonToXml(String jsonFile, String xmlFile) throws XmlConversionException;

}
