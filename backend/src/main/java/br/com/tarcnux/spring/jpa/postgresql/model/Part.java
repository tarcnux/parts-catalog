package br.com.tarcnux.spring.jpa.postgresql.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "applicationVehicle")
	private String applicationVehicle;

	@Column(name = "netWeight")
	private Double netWeight;

	@Column(name = "grossWeight")
	private Double grossWeight;

	@ManyToMany
	@JoinTable(name = "part_vehicle", joinColumns = @JoinColumn(name = "part_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
	private Set<Vehicle> vehicles = new HashSet<>();

	public Part() {
	}

	public Part(String name, String applicationVehicle, Double netWeight, Double grossWeight) {
		this.name = name;
		this.applicationVehicle = applicationVehicle;
		this.netWeight = netWeight;
		this.grossWeight = grossWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplicationVehicle() {
		return applicationVehicle;
	}

	public void setApplicationVehicle(String applicationVehicle) {
		this.applicationVehicle = applicationVehicle;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Long getId() {
		return id;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
		vehicle.getParts().add(this);
	}

	public void removeVehicle(Vehicle vehicle) {
		this.vehicles.remove(vehicle);
		vehicle.getParts().remove(this);
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", applicationVehicle=" + applicationVehicle + ", netWeight="
				+ netWeight + ", grossWeight=" + grossWeight + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
