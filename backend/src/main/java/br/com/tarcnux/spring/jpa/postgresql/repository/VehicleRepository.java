package br.com.tarcnux.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarcnux.spring.jpa.postgresql.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findByOrderByNameAsc();
	List<Vehicle> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
}
