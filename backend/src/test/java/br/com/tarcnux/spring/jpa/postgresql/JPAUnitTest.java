package br.com.tarcnux.spring.jpa.postgresql;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tarcnux.spring.jpa.postgresql.model.Part;
import br.com.tarcnux.spring.jpa.postgresql.repository.PartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PartRepository repository;
	
	@Test
	public void should_find_no_parts_if_repository_is_empty() { //1
		Iterable<Part> parts = repository.findAll();
		
		assertThat(parts).isEmpty();
	}
	
	@Test
	public void should_store_a_part() { //2
		Part part = repository.save(new Part("Impulsor de Partida", "Palio", 5.67, 6.54));
		
		assertThat(part).hasFieldOrPropertyWithValue("name", "Impulsor de Partida");
		assertThat(part).hasFieldOrPropertyWithValue("applicationVehicle", "Palio");
		assertThat(part).hasFieldOrPropertyWithValue("netWeight", 5.67);
		assertThat(part).hasFieldOrPropertyWithValue("grossWeight", 6.54);
	}
	
	@Test
	public void should_find_all_parts() { //3
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Celta", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		Iterable<Part> parts = repository.findAll();
		
		assertThat(parts).hasSize(4).contains(part1, part2, part3, part4);
	}
	
	@Test
	public void should_find_part_by_id() { //4
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Celta", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		Part foundPart = repository.findById(part3.getId()).get();
		
		assertThat(foundPart).isEqualTo(part3);
	}
	
	@Test
	public void should_find_part_by_name_containing_string() { //5
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Celta", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		Iterable<Part> parts = repository.findByNameContaining("Polia");
		assertThat(parts).hasSize(3).contains(part2, part3, part4);
	}
	
	@Test
	public void should_find_part_by_applicationVehicle_containing_string() { //6
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Marea", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		Iterable<Part> parts = repository.findByapplicationVehicleContaining("Mar");
		assertThat(parts).hasSize(2).contains(part2, part3);
	}
	
	@Test
	public void should_update_part_by_id() { //7
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Celta", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		Part updatedPart3 = new Part("Updated Polia Roda Livre", "Updated Marea", 19.87, 20.98);
		
		Part testPart3 = repository.findById(part3.getId()).get();
		testPart3.setName(updatedPart3.getName());
		testPart3.setApplicationVehicle(updatedPart3.getApplicationVehicle());
		testPart3.setNetWeight(updatedPart3.getNetWeight());
		testPart3.setGrossWeight(updatedPart3.getGrossWeight());
		
		repository.save(testPart3);
		
		Part checkPart = repository.findById(part3.getId()).get();
		
		assertThat(checkPart.getId()).isEqualTo(part3.getId());
		assertThat(checkPart.getName()).isEqualTo(part3.getName());
		assertThat(checkPart.getApplicationVehicle()).isEqualTo(part3.getApplicationVehicle());
		assertThat(checkPart.getNetWeight()).isEqualTo(part3.getNetWeight());
		assertThat(checkPart.getGrossWeight()).isEqualTo(part3.getGrossWeight());
		
	}
	
	@Test
	public void should_delete_part_by_id() { //8
		Part part1 = new Part("Impulsor de Partida", "Palio", 5.67, 6.54);
		entityManager.persist(part1);
		
		Part part2 = new Part("Polia Rígida", "Celta", 4.56, 5.43);
		entityManager.persist(part2);
		
		Part part3 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);
		entityManager.persist(part3);
		
		Part part4 = new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98);
		entityManager.persist(part4);
		
		repository.deleteById(part2.getId());
		repository.deleteById(part4.getId());
		
		Iterable<Part> parts = repository.findAll();
		
		assertThat(parts).hasSize(2).contains(part1, part3);
	}
	
	@Test
	public void should_delete_all_tutorials() { //9
		entityManager.persist(new Part("Impulsor de Partida", "Palio", 5.67, 6.54));
		entityManager.persist(new Part("Polia Rígida", "Celta", 4.56, 5.43));
		entityManager.persist(new Part("Polia Roda Livre", "Marea", 9.87, 10.98));
		entityManager.persist(new Part("Tensor, Polia Desviadora e Kit de Distribuição", "Fusca", 9.87, 10.98));
		
		repository.deleteAll();
		
		assertThat(repository.findAll()).isEmpty();
		
	}
	

}
