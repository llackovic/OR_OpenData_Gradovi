package hr.fer.or.opendatagradovi.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.or.opendatagradovi.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{
	
	Optional<List<City>> findAllByContinent(String continent);
	Optional<City> findByName(String name);

}
