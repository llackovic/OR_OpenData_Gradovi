package hr.fer.or.opendatagradovi.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHomepage(Model model) {
		
		return "index";
	}
	
	@GetMapping("/downloadcsv")
	public void getCsv(HttpServletResponse response) {
		
		try {
		      // get your file as InputStream
		      InputStream is = new FileInputStream(new File("C:\\Users\\lukal\\eclipse-workspace\\or\\open-data-gradovi\\src\\main\\resources\\static\\data\\gradovi.csv"));
		      // copy it to response's OutputStream
		      IOUtils.copy(is, response.getOutputStream());
		      response.flushBuffer();
		    } catch (IOException ex) {
		    	
		      throw new RuntimeException("IOError writing file to output stream");
		    }
	}
	
	@GetMapping("/downloadjson")
	public void getJson(HttpServletResponse response) {
		
		try {
		      // get your file as InputStream
		      InputStream is = new FileInputStream(new File("C:\\Users\\lukal\\eclipse-workspace\\or\\open-data-gradovi\\src\\main\\resources\\static\\data\\gradovi.json"));
		      // copy it to response's OutputStream
		      IOUtils.copy(is, response.getOutputStream());
		      response.flushBuffer();
		    } catch (IOException ex) {
		    	
		      throw new RuntimeException("IOError writing file to output stream");
		    }
	}

}
