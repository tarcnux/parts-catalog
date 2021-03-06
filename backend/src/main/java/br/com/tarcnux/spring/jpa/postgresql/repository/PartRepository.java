package br.com.tarcnux.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarcnux.spring.jpa.postgresql.model.Part;

public interface PartRepository extends JpaRepository<Part, Long>{
	
	List<Part> findByOrderByNameAsc();
	List<Part> findByNameContaining(String name);
	List<Part> findByapplicationVehicleContaining(String vehicle);

}
