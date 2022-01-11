package hr.fer.or.opendatagradovi.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hr.fer.or.opendatagradovi.repositories.CityRepository;

@Controller
public class HomeController {
	
	private CityRepository cityRepo;
	
	public HomeController(CityRepository cityRepo) {
		this.cityRepo = cityRepo;
	}

	
	
	@GetMapping("/")
	public String showHomepage(Model model, @AuthenticationPrincipal OidcUser principal) {
		
		return "index";
	}
	
	@GetMapping("/userinfo")
    public String userInfo(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "userinfo";
    }
	
	@GetMapping("/refreshcopies")
	public String refreshCopies() {
		
		//cityRepo.refreshCopies();
		
		
		
		return "index";
	}
	
	@GetMapping("/downloadcsv")
	public void getCsv(HttpServletResponse response) {
		
		try {
		      
			response.setContentType("text/csv");
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
			
			response.setContentType("application/json");
			response.setHeader("Content-Disposition", "attachment; filename=gradovi.json");
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
