package hr.fer.or.opendatagradovi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grad")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv", nullable = false, unique = true, length = 100)
	private String name;
	
	@Column(name = "drzava", length = 50)
	private String country;
	
	@Column(name = "kontinent", length = 50)
	private String continent;
	
	@Column(name = "povrsina")
	private Double surface;
	
	@Column(name = "nadmorska_visina")
	private Integer elevation;
	
	@Column(name = "broj_stanovnika")
	private Integer population;
	
	@Column(name = "gustoca_naseljenosti")
	private Integer populationDensity;
	
	@Column(name = "gradonacelnik", length = 50)
	private String mayor;
	
	@Column(name = "web", length = 200)
	private String web;
	
	@Column(name = "vremenska_zona")
	private Integer timezone;
	
	@Column(name = "glavni_grad")
	private Boolean capital;
	
	@OneToMany(mappedBy = "cityId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<District> districts;
	
	public City() {
		
	}

	public City(String name, String country, String continent, Double surface, Integer elevation, Integer population,
			Integer populationDensity, String mayor, String web, Integer timezone, Boolean capital) {
		super();
		this.name = name;
		this.country = country;
		this.continent = continent;
		this.surface = surface;
		this.elevation = elevation;
		this.population = population;
		this.populationDensity = populationDensity;
		this.mayor = mayor;
		this.web = web;
		this.timezone = timezone;
		this.capital = capital;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Double getSurface() {
		return surface;
	}

	public void setSurface(Double surface) {
		this.surface = surface;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getPopulationDensity() {
		return populationDensity;
	}

	public void setPopulationDensity(Integer populationDensity) {
		this.populationDensity = populationDensity;
	}

	public String getMayor() {
		return mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}
	

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + ", continent=" + continent + ", surface="
				+ surface + ", elevation=" + elevation + ", population=" + population + ", populationDensity="
				+ populationDensity + ", mayor=" + mayor + ", web=" + web + ", timezone=" + timezone + ", capital="
				+ capital + "]";
	}
	
	
	

}
