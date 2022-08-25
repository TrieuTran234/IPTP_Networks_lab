package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.entity.xmlFile;
import com.uitils.DataService;
import com.uitils.LoadData;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home(Model model) throws ParserConfigurationException, SAXException, IOException, ParseException {
		DataService aData = new LoadData();
		model.addAttribute("data", aData.loadData());
		return "table";
	}

	@RequestMapping("/home/find")
	public String find(Model model, HttpServletRequest req)
			throws ParserConfigurationException, SAXException, IOException, ParseException {

		DataService aData = new LoadData();
		List<xmlFile> list = new ArrayList<>();

		if (!req.getParameter("keywords").isEmpty()) {
			String keyword = req.getParameter("keywords");
			list = aData.findByName(keyword);
			model.addAttribute("keywords", keyword);
		} else {
			list = aData.loadData();
		}
		if (list.isEmpty()) {
			model.addAttribute("error", "No results were found!");
		}
		model.addAttribute("data", list);
		return "table";
	}

	@RequestMapping("/home/workingtime")
	public String workingtime(Model model, HttpServletRequest req)
			throws ParserConfigurationException, SAXException, IOException, ParseException {

		DataService aData = new LoadData();
		model.addAttribute("data", aData.loadData());
		return "table2";
	}

	@RequestMapping("/home/workingtime/find")
	public String wTimeFind(Model model, HttpServletRequest req)
			throws ParserConfigurationException, SAXException, IOException, ParseException {
		DataService aData = new LoadData();
		List<xmlFile> list = new ArrayList<>();

		String keywords = req.getParameter("keywords");

		if (!req.getParameter("keywords").isEmpty()) {
			list = aData.findByName(keywords);
		} else {
			list = aData.loadData();
		}
		if (list.isEmpty()) {
			model.addAttribute("error", "No results were found!");
		}
		model.addAttribute("keywords", keywords);

		if (!req.getParameter("time1").isEmpty() && !req.getParameter("time1").isEmpty()) {

			String time = req.getParameter("time1");
			String time2 = req.getParameter("time2");

			model.addAttribute("time1", time);
			model.addAttribute("time2", time2);

			List<xmlFile> dateList = new ArrayList<>();

			for (xmlFile xmlFile : list) {
				boolean o = aData.isInHour(time, time2, xmlFile);
				if (o) {
					dateList.add(xmlFile);
				}
			}
			if (dateList.isEmpty()) {
				model.addAttribute("error", "No results were found!");
			}
			model.addAttribute("data", dateList);
		} else {

			model.addAttribute("data", list);
		}

		return "/table2";
	}
}
