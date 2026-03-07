package br.com.tarcnux.spring.jpa.postgresql;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.tarcnux.spring.jpa.postgresql.controller.PartController;
import br.com.tarcnux.spring.jpa.postgresql.model.Part;
import br.com.tarcnux.spring.jpa.postgresql.repository.PartRepository;

@WebMvcTest(PartController.class)
class PartControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PartRepository partRepository;

	@Test
	void should_prioritize_vehicle_filter_when_name_and_vehicle_are_informed() throws Exception {
		Part part1 = new Part("Polia Rígida", "Marea", 4.56, 5.43);
		Part part2 = new Part("Polia Roda Livre", "Marea", 9.87, 10.98);

		when(partRepository.findByApplicationVehicleContainingIgnoreCaseOrderByNameAsc("Mar"))
				.thenReturn(Arrays.asList(part1, part2));

		mockMvc.perform(get("/api/v1/parts").param("name", "Polia").param("vehicle", "Mar"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].applicationVehicle").value("Marea"))
				.andExpect(jsonPath("$[1].applicationVehicle").value("Marea"));

		verify(partRepository).findByApplicationVehicleContainingIgnoreCaseOrderByNameAsc("Mar");
		verify(partRepository, never()).findByNameContainingIgnoreCaseOrderByNameAsc(anyString());
	}
}
