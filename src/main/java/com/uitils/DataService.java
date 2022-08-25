package com.uitils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.entity.xmlFile;

public interface DataService {

	ArrayList<String> loadNameDistinct() throws ParserConfigurationException, SAXException, IOException ;

	List<xmlFile> findByName(String name) throws ParserConfigurationException, SAXException, IOException ;

	List<xmlFile> loadData() throws ParserConfigurationException, SAXException, IOException;

	boolean isInHour(String startHour, String endHour, xmlFile xmlFile) throws ParseException;

	String toString(Date date, String pattern);
	
	Date toDate(String date, String pattern);
	

}
