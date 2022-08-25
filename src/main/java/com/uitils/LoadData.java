package com.uitils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.entity.xmlFile;

public class LoadData implements DataService {
	@Override
	public List<xmlFile> loadData() throws ParserConfigurationException, SAXException, IOException {

		// create file
		File f = new File("src\\main\\java\\com\\data\\data_java-2.xml");
		// create document contain xml info
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder buider = factory.newDocumentBuilder();
		Document doc = buider.parse(f);

		// root element <response>
		Element element = doc.getDocumentElement();

		// get info <bar>
		NodeList studentList = element.getElementsByTagName("bar");

		List<xmlFile> listXml = new ArrayList<xmlFile>();
		for (int i = 0; i < studentList.getLength(); i++) {
			Node node = studentList.item(i);
			Element student = (Element) node;
			xmlFile xmlFile = new xmlFile();

			xmlFile.setName(student.getAttribute("name"));
			xmlFile.setEnddate(toDate(student.getAttribute("enddate"), "yyyy-MM-dd hh:mm:ss"));
			xmlFile.setStartdate(toDate(student.getAttribute("startdate"), "yyyy-MM-dd hh:mm:ss"));
			xmlFile.setIsdbar(Boolean.parseBoolean(student.getAttribute("isdbar")));
			xmlFile.setDcname(student.getAttribute("dcname"));
			xmlFile.setId(Integer.parseInt(student.getAttribute("id")));

			listXml.add(xmlFile);
		}
		return listXml;
	}

	@Override
	public List<xmlFile> findByName(String name) throws ParserConfigurationException, SAXException, IOException {
		List<xmlFile> listByName = new ArrayList<>();
		List<xmlFile> listXml = loadData();
		for (xmlFile xmlFile : listXml) {
			if (xmlFile.getName().equalsIgnoreCase(name)) {
				listByName.add(xmlFile);
			}
		}
		return listByName;
	}

	@Override
	public ArrayList<String> loadNameDistinct() throws ParserConfigurationException, SAXException, IOException {
		List<xmlFile> listXml = loadData();
		ArrayList<String> name = new ArrayList<>();

		for (int i = 0; i < listXml.size(); i++) {
			if (!name.contains(listXml.get(i).getName())) {
				name.add(listXml.get(i).getName());
			}
		}
		System.out.println("so nhan vien: " + name.size());
		return name;
	}

	static SimpleDateFormat formater = new SimpleDateFormat();


	// Đổi định dạng từ date to String
	@Override
	public String toString(Date date, String pattern) {
		formater.applyPattern(pattern);
		return formater.format(date);
	}

	// kiem tra thoi gian
	@Override
	public boolean isInHour(String startHour, String endHour, xmlFile xmlFile) throws ParseException {

		boolean result = false;
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		
		Date start = hourFormat.parse(startHour);
		Date end = hourFormat.parse(endHour);
		
		Date starDate = xmlFile.getStartdate();
		Date endDate = xmlFile.getEnddate();

		String staHourStr = hourFormat.format(starDate.getTime());
		String endHourStr = hourFormat.format(endDate.getTime());
	
		try {
			Date nowHour = hourFormat.parse(staHourStr);
			if (nowHour.after(start) && nowHour.before(end) || (nowHour.equals(start) || (nowHour.equals(end)))) {
				result = true;
			}

		} catch (ParseException e) {
			result = false;
		}
		try {
			Date nowHour = hourFormat.parse(endHourStr);
			if (nowHour.after(start) && nowHour.before(end) || (nowHour.equals(start) || (nowHour.equals(end)))) {
				result = true;
			}

		} catch (ParseException e) {
			result = false;
		}

		return result;
	}

	@Override
	public Date toDate(String date, String pattern) {
		try {
            formater.applyPattern(pattern);
            return  formater.parse(date);
        } catch (ParseException ex) {
            throw  new RuntimeException(ex);
        }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
