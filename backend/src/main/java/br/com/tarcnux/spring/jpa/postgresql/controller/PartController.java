package br.com.tarcnux.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarcnux.spring.jpa.postgresql.model.Part;
import br.com.tarcnux.spring.jpa.postgresql.repository.PartRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
@Api(value = "Part")
public class PartController {
	
	@Autowired
	PartRepository partRepository;
	
	@GetMapping("/parts")
	@ApiOperation(value = "Exibe todas as peças em ordem alfabética")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Part>> getAllParts(
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String vehicle) {
		
		try {
			
			List<Part> parts = new ArrayList<Part>();
			
			if (name == null && vehicle == null) {
				partRepository.findByOrderByNameAsc().forEach(parts::add);
			} else if (vehicle == null) {
				partRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name).forEach(parts::add);
			} else {
				partRepository.findByapplicationVehicleContainingIgnoreCaseOrderByNameAsc(vehicle).forEach(parts::add);
			}
			
			if(parts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Part>>(parts, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/parts/{id}")
	@ApiOperation(value = "Exibe uma peça específica")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Part> getPartById(@PathVariable("id") Long id) {
		Optional<Part> partData = partRepository.findById(id);
		
		if (partData.isPresent()) {
			return new ResponseEntity<Part>(partData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/parts")
	@ApiOperation(value = "Cadastra uma peça")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Part> createPart(@RequestBody Part part) {
		try {
			Part _part = partRepository
					.save(new Part(
							part.getName(),
							part.getApplicationVehicle(),
							part.getNetWeight(),
							part.getGrossWeight()
							));
			return new ResponseEntity<Part>(_part, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/parts/{id}")
	@ApiOperation(value = "Atualiza uma peça específica")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Part> updatePart(
			@PathVariable("id") Long id,
			@RequestBody Part part
			) {
		Optional<Part> partData = partRepository.findById(id);
		
		if(partData.isPresent()) {
			Part _part = partData.get();
			_part.setName(part.getName());
			_part.setApplicationVehicle(part.getApplicationVehicle());
			_part.setNetWeight(part.getNetWeight());
			_part.setGrossWeight(part.getGrossWeight());
			return new ResponseEntity<Part>(partRepository.save(_part), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/parts/{id}")
	@ApiOperation(value = "Exclui uma peça")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deletePart(@PathVariable("id") Long id) {
		try {
			partRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/parts")
	@ApiOperation(value = "Exclui todas as peças")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteAllParts() {
		try {
			partRepository.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
