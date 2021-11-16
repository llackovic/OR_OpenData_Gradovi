package hr.fer.or.opendatagradovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gradska_cetvrt")
public class District {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv", nullable = false, unique = true, length = 100)
	private String name;
	
	@Column(name = "povrsina")
	private Double surface;
	
	@Column(name = "broj_stanovnika")
	private Integer population;
	
	@Column(name = "gustoca_naseljenosti")
	private Integer populationDensity;
	
	@ManyToOne
	@JoinColumn(name = "grad_id")
	private City cityId;
	
	public District() {
		
	}

	public District(String name, Double surface, Integer population, Integer populationDensity, City city) {
		super();
		this.name = name;
		this.surface = surface;
		this.population = population;
		this.populationDensity = populationDensity;
		this.cityId = city;
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

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City city) {
		this.cityId = city;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + ", surface=" + surface + ", population=" + population
				+ ", populationDensity=" + populationDensity + ", cityId=" + cityId + "]";
	}
	
	

}
