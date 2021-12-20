package hr.fer.or.opendatagradovi.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.or.opendatagradovi.entities.City;
import hr.fer.or.opendatagradovi.entities.District;
import hr.fer.or.opendatagradovi.entities.DistrictRepresentation;
import hr.fer.or.opendatagradovi.entities.ResponseWrapper;
import hr.fer.or.opendatagradovi.repositories.CityRepository;
import hr.fer.or.opendatagradovi.repositories.DistrictRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class APIController {
	
	private CityRepository cityRepo;
	private DistrictRepository districtRepo;

	public APIController(CityRepository cityRepo, DistrictRepository districtRepo) {
		this.cityRepo = cityRepo;
		this.districtRepo = districtRepo;
	}
	
	
	@Operation(summary = "Get all cities from collection")
	@GetMapping(value = "/cities", produces = "application/json")
	public ResponseEntity<ResponseWrapper<City>> getAllCities(){
		
		ResponseWrapper<City> response = new ResponseWrapper<>("OK", "Fetched cities objects", cityRepo.findAll());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		
	}
	
	@Operation(summary = "Get all cities from collection using ID")
	@GetMapping(value = "/cities/{id}", produces = "application/json")
	public ResponseEntity<ResponseWrapper<City>> getCityById(@PathVariable Long id){
		
		if(cityRepo.findById(id).isPresent()) {
			
			ResponseWrapper<City> response = new ResponseWrapper<>("OK", "Fetched city object", List.of(cityRepo.findById(id).get()));
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else {
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Not Found", "City with the provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	@Operation(summary = "Get all districts from city with provided ID")
	@GetMapping(value = "/cities/{id}/districts", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> getAllDistrictsFromCity(@PathVariable Long id){
		
		if(cityRepo.findById(id).isPresent()) {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("OK", "Fetched districts from city", cityRepo.findById(id).get().getDistricts());
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "City with the provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	@Operation(summary = "Get all districts from collection")
	@GetMapping(value = "/districts", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> getAllDistricts(){
		
		ResponseWrapper<District> response = new ResponseWrapper<>("OK", "Fetched districts objects", districtRepo.findAll());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		
	}
	
	@Operation(summary = "Get all districts from collection with provided ID")
	@GetMapping(value = "/districts/{id}", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> getDistrictById(@PathVariable Long id){
		
		if(districtRepo.findById(id).isPresent()) {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("OK", "Fetched district object", List.of(districtRepo.findById(id).get()));
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "District with the provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	
	@Operation(summary = "Save a city to collection")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "201", description = "Created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ResponseWrapper.class)) }),
			  @ApiResponse(responseCode = "409", description = "Conflict", 
			    content = { @Content(mediaType = "application/json", 
				  schema = @Schema(implementation = ResponseWrapper.class)) }) })
	@PostMapping(value = "/cities", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseWrapper<City>> saveCity(@RequestBody City city) throws URISyntaxException{
		
		if(cityRepo.findByName(city.getName()).isEmpty()) {
			
			City savedEntity = cityRepo.save(city);
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Created", "Created city object", List.of(savedEntity));
			
			return ResponseEntity.created(new URI("/cities/" + savedEntity.getId())).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		
		}else {
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Conflict", "Provided name already exists", null);
			
			return ResponseEntity.status(HttpStatus.CONFLICT).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
		
	}
	
	@Operation(summary = "Save a district to collection")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "201", description = "Created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ResponseWrapper.class)) }),
			  @ApiResponse(responseCode = "409", description = "Conflict", 
			    content = { @Content(mediaType = "application/json", 
				  schema = @Schema(implementation = ResponseWrapper.class)) }) })
	@PostMapping(value = "/districts", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> saveDistrict(@RequestBody DistrictRepresentation district) throws URISyntaxException{
		
		if(cityRepo.existsById(district.getCityId())) {
			
			District districtForSave = new District(district.getName(), district.getSurface(), district.getPopulation(), district.getPopulationDensity(), cityRepo.findById(district.getCityId()).get());
			
			District savedEntity = districtRepo.save(districtForSave);
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Created", "Created district object", List.of(savedEntity));
			
			return ResponseEntity.created(new URI("/districts/" + savedEntity.getId())).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		
		}else {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "District can't be saved because city with provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
		
	}
	
	@Operation(summary = "Update city with provided ID")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "201", description = "Created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ResponseWrapper.class)) }),
			  @ApiResponse(responseCode = "409", description = "Conflict", 
			    content = { @Content(mediaType = "application/json", 
				  schema = @Schema(implementation = ResponseWrapper.class)) }) })
	@PutMapping(value = "/cities/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseWrapper<City>> updateCity(@PathVariable Long id, @RequestBody City city) throws URISyntaxException{
		
		if(cityRepo.findById(id).isPresent()) {
			
			City cityFound = cityRepo.findById(id).get();
			cityFound.updateAll(city);
			
			ResponseWrapper<City> response = new ResponseWrapper<City>("Updated", "Updated city object", List.of(cityRepo.save(cityFound)));
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else if(cityRepo.findByName(city.getName()).isEmpty()){
			
			City newCity = new City(city.getName(), city.getCountry(), city.getContinent(), city.getSurface(), city.getElevation(), city.getPopulation(), city.getPopulationDensity(), city.getMayor(), city.getWeb(), city.getTimezone(), city.getCapital());
			
			City savedEntity = cityRepo.save(newCity);
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Created", "Created city object", List.of(savedEntity));
			
			return ResponseEntity.created(new URI("/cities/" + savedEntity.getId())).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		
		}else {
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Conflict", "Provided ID not exists, but name exists so object can't be created or updated", null);
			
			return ResponseEntity.status(HttpStatus.CONFLICT).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	@Operation(summary = "Update district with provided ID")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "201", description = "Created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ResponseWrapper.class)) }) })
	@PutMapping(value = "/districts/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> updateDistrict(@PathVariable Long id, @RequestBody DistrictRepresentation district) throws URISyntaxException{
		
		if(districtRepo.findById(id).isPresent()) {
			
			if(cityRepo.existsById(district.getCityId())) {
				
				District districtForUpdate = new District(district.getName(), district.getSurface(), district.getPopulation(), district.getPopulationDensity(), cityRepo.findById(district.getCityId()).get());
				
				District districtFound = districtRepo.findById(id).get();
				districtFound.updateAll(districtForUpdate);
				
				ResponseWrapper<District> response = new ResponseWrapper<>("Updated", "Updated district object", List.of(districtRepo.save(districtFound)));
				
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
			}else {
				
				ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "District can't be updated because city with provided ID does not exists", null);
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			}
			
			
			
		}else {
			
			if(cityRepo.existsById(district.getCityId())) {
				
				District districtForSave = new District(district.getName(), district.getSurface(), district.getPopulation(), district.getPopulationDensity(), cityRepo.findById(district.getCityId()).get());
				
				District savedEntity = districtRepo.save(districtForSave);
				
				ResponseWrapper<District> response = new ResponseWrapper<>("Created", "Created district object", List.of(savedEntity));
				
				return ResponseEntity.created(new URI("/districts/" + savedEntity.getId())).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
			}else {
				
				ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "District can't be saved because city with provided ID does not exists", null);
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			}
		}
		
	}
	
	@Operation(summary = "Delete city with provided ID")
	@DeleteMapping(value = "/cities/{id}", produces = "application/json")
	public ResponseEntity<ResponseWrapper<City>> deleteCity(@PathVariable Long id){
		
		if(cityRepo.existsById(id)) {
			
			cityRepo.deleteById(id);
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Deleted", "Deleted city object", null);
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else {
			
			ResponseWrapper<City> response = new ResponseWrapper<>("Not Found", "City with provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	@Operation(summary = "Delete district with provided ID")
	@DeleteMapping(value = "/district/{id}", produces = "application/json")
	public ResponseEntity<ResponseWrapper<District>> deleteDistrict(@PathVariable Long id){
		
		if(districtRepo.existsById(id)) {
			
			districtRepo.deleteById(id);
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Deleted", "Deleted district object", null);
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
			
		}else {
			
			ResponseWrapper<District> response = new ResponseWrapper<>("Not Found", "District with provided ID does not exists", null);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
		}
		
	}
	
	

}
