package com.converter;

import com.util.XmlConversionException;

public class JsonToXmlApplication {
	
	public static void main(String[] args) throws XmlConversionException {
		if (args.length == 0) {
			throw new XmlConversionException("Please provide json file to read json");
        } else if(args.length == 1) {
        	throw new XmlConversionException("Please provide xml file to write the output");
        } else {
        	new JsonToXmlConverter().convertJsonToXml(args[0], args[1]);
        }
		
	}
}
