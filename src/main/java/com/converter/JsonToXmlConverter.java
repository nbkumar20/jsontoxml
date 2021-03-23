package com.converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.activation.UnknownObjectException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.util.XmlConversionException;

public class JsonToXmlConverter implements XMLJSONConverterI {
	private static final Logger logger = LogManager.getLogger(JsonToXmlConverter.class.getName());  

	@Override
	  public void convertJsonToXml(String jsonFile, String xmlFile) throws XmlConversionException {
	    Object jsonData = null;
	    logger.info("Json File: " + jsonFile + " Xml File path : " + xmlFile);
	    try {
	    	
	      jsonData = new JSONObject(readFile(jsonFile));
	      logger.debug("Json data: " + jsonData);
	      writeFile(xmlFile, jsonFile, convertToXml(null, jsonData));
		} catch (UnknownObjectException e) {
			logger.info("Invalid data type found in Json: " + e.getMessage());
		}
	  }
	
	
	private String readFile(String filepath) throws XmlConversionException
    {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
        	builder.append(currentLine);
          } 
        } catch (IOException e) {
        	logger.info("Exception while reading data from file: " + e.getMessage());
        	throw new XmlConversionException("Exception while reading data from file", e);
		}
        return builder.toString();
    }
    
    /**
     * Method that writes the xml data to file
     * 
     * @param xmlFile
     * @param fileName
     * @param xmlData
     * @throws XmlConversionException
     */
	private void writeFile(String xmlFile, String fileName, String xmlData) throws XmlConversionException
    {
    	logger.debug("Xml data: " + xmlData);
    	File inputData = new File(xmlFile);
    	fileName = new File(fileName).getName();
    	FileWriter fileWriter = null;
    	BufferedWriter writer = null;
    	try {
    	if(inputData.isDirectory()) {
    		//If the given input is directory, creating xml file with the name of the input json file
    		fileWriter = new FileWriter(new File(xmlFile + "/" + fileName.substring(0, fileName.lastIndexOf("."))+".xml"));
    	} else if(inputData.isAbsolute()){
    		fileWriter = new FileWriter(xmlFile);
    	} else {
    		throw new XmlConversionException("Invalid file/Directory");
    	}
    		writer = new BufferedWriter(fileWriter);
			writer.write(xmlData);
		} catch (IOException e) {
			throw new XmlConversionException("Exception while Writing data to file", e);
		}
    	finally {
    		if(null != writer) {
    			try {
					writer.close();
				} catch (IOException e) {
					throw new XmlConversionException("Exception while Closing the writer");
				}
    		}
    	}
		logger.info("Xml data successfully saved to the file : " + xmlFile);
    }

    /**
     * Method that converts the input json to xml string
     * 
     * @param name
     * @param data
     * @return
     * @throws UnknownObjectException
     */
	  private String convertToXml(String name, Object data) throws UnknownObjectException {
	    String nameValue = (name == null) ? "" : " name=\"" + name + "\"";
	    if (data instanceof Number) {
	      return "<number" + nameValue + ">" + data + "</number>";
	    } else if (data instanceof String) {
	      return "<string" + nameValue + ">" + data + "</string>";
	    } else if (data instanceof Boolean) {
	      return "<boolean" + nameValue + ">" + data + "</boolean>";
	    } else if (data instanceof JSONArray) {
	      StringBuilder builder = new StringBuilder("<array" + nameValue + ">");
	      for (int i = 0; i < ((JSONArray) data).length(); i++) {
	        builder.append(convertToXml(null, ((JSONArray) data).get(i)));
	      }
	      builder.append("</array>");
	      return builder.toString();
	    } else if (data instanceof JSONObject) {
	      StringBuilder builder = new StringBuilder("<object" + nameValue + ">");
	      Iterator<String> it = ((JSONObject) data).keys();
	      while (it.hasNext()) {
	        String key = it.next();
	        Object objData = ((JSONObject) data).get(key);
	        builder.append(convertToXml(key, objData));
	      }
	      builder.append("</object>");
	      return builder.toString();
	    } else if (data == JSONObject.NULL) {
	      return "<null" + nameValue + "/>";
	    } else {
	      throw new UnknownObjectException("Data type "
	          + data.getClass() + " not yet supported");
	    }
	  }

}
