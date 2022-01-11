package hr.fer.or.opendatagradovi.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.fer.or.opendatagradovi.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{
	
	Optional<List<City>> findAllByContinent(String continent);
	Optional<City> findByName(String name);
	
	@Query(value = "COPY("
			+ "	SELECT"
			+ "		grad.id,"
			+ "		grad.naziv,"
			+ "		drzava,"
			+ "		kontinent,"
			+ "		grad.povrsina,"
			+ "		nadmorska_visina,"
			+ "		grad.broj_stanovnika,"
			+ "		grad.gustoca_naseljenosti,"
			+ "		gradonacelnik,"
			+ "		web,"
			+ "		vremenska_zona,"
			+ "		glavni_grad,"
			+ "		gradska_cetvrt.id,"
			+ "		gradska_cetvrt.naziv,"
			+ "		gradska_cetvrt.povrsina,"
			+ "		gradska_cetvrt.broj_stanovnika,"
			+ "		gradska_cetvrt.gustoca_naseljenosti"
			+ "	FROM grad JOIN gradska_cetvrt ON gradska_cetvrt.grad_id=grad.id)"
			+ "TO 'C:\\Users\\Public\\gradovi.csv' with DELIMITER ',' CSV HEADER;"
			+ ""
			+ "COPY ("
			+ "	SELECT to_json(array_agg(t)) FROM ("
			+ "			SELECT"
			+ "				grad.id,"
			+ "				grad.naziv,"
			+ "				drzava,"
			+ "				kontinent,"
			+ "				grad.povrsina,"
			+ "				nadmorska_visina,"
			+ "				grad.broj_stanovnika,"
			+ "				grad.gustoca_naseljenosti,"
			+ "				gradonacelnik,"
			+ "				web,"
			+ "				vremenska_zona,"
			+ "				glavni_grad,"
			+ "				json_agg("
			+ "					json_build_object("
			+ "						'id', gradska_cetvrt.id,"
			+ "						'naziv', gradska_cetvrt.naziv,"
			+ "						'povrsina', gradska_cetvrt.povrsina,"
			+ "						'broj_stanovnika', gradska_cetvrt.broj_stanovnika,"
			+ "						'gustoca_naseljenosti', gradska_cetvrt.gustoca_naseljenosti)) AS gradske_cetvrti"
			+ "			FROM grad"
			+ "				JOIN gradska_cetvrt ON gradska_cetvrt.grad_id=grad.id"
			+ "			GROUP BY grad.id"
			+ "			ORDER BY grad.id"
			+ "			) t )"
			+ "TO 'C:\\Users\\Public\\gradovi.json';"
			+ ""
			+ "SELECT * FROM grad"
			, nativeQuery = true)
	Optional<List<City>> refreshCopies();

}
