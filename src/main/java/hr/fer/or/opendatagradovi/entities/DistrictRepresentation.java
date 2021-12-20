package hr.fer.or.opendatagradovi.entities;


public class DistrictRepresentation {
	
private String name;
	
	private Double surface;
	
	private Integer population;
	
	private Integer populationDensity;
	
	private Long cityId;
	
	public DistrictRepresentation() {
		
	}

	public DistrictRepresentation(String name, Double surface, Integer population, Integer populationDensity,
			Long cityId) {
		this.name = name;
		this.surface = surface;
		this.population = population;
		this.populationDensity = populationDensity;
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSurface() {
		return surface;
	}

	public void setSurface(Double surface) {
		this.surface = surface;
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	

}
