package hr.fer.or.opendatagradovi.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hr.fer.or.opendatagradovi.entities.City;
import hr.fer.or.opendatagradovi.repositories.CityRepository;

@Controller
public class TableController {
	
	private CityRepository cityRepo;
	
	public TableController(CityRepository cityRepo) {
		this.cityRepo = cityRepo;
	}
	
	@GetMapping("/datatable")
	public String showDatatableCity(Model model) {
		
		List<City> citiesAll = cityRepo.findAll();
		
		model.addAttribute("citiesList", citiesAll);
		
		return "datatable";
	}

}
