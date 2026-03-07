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

import br.com.tarcnux.spring.jpa.postgresql.model.Vehicle;
import br.com.tarcnux.spring.jpa.postgresql.repository.VehicleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
@Api(value = "Vehicle")
public class VehicleController {

	@Autowired
	VehicleRepository vehicleRepository;

	@GetMapping("/vehicles")
	@ApiOperation(value = "Exibe todos os veículos em ordem alfabética")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam(required = false) String name) {
		try {
			List<Vehicle> vehicles = new ArrayList<>();
			if (name == null) {
				vehicleRepository.findByOrderByNameAsc().forEach(vehicles::add);
			} else {
				vehicleRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name).forEach(vehicles::add);
			}

			if (vehicles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(vehicles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/vehicles/{id}")
	@ApiOperation(value = "Exibe um veículo específico")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {
		Optional<Vehicle> vehicleData = vehicleRepository.findById(id);
		if (vehicleData.isPresent()) {
			return new ResponseEntity<>(vehicleData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/vehicles")
	@ApiOperation(value = "Cadastra um veículo")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		try {
			Vehicle _vehicle = vehicleRepository.save(new Vehicle(vehicle.getName()));
			return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/vehicles/{id}")
	@ApiOperation(value = "Atualiza um veículo")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) {
		Optional<Vehicle> vehicleData = vehicleRepository.findById(id);
		if (vehicleData.isPresent()) {
			Vehicle _vehicle = vehicleData.get();
			_vehicle.setName(vehicle.getName());
			return new ResponseEntity<>(vehicleRepository.save(_vehicle), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/vehicles/{id}")
	@ApiOperation(value = "Exclui um veículo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable("id") Long id) {
		try {
			vehicleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/vehicles")
	@ApiOperation(value = "Exclui todos os veículos")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteAllVehicles() {
		try {
			vehicleRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
