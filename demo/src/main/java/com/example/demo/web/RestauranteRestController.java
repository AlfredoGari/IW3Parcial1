package com.example.demo.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IRestauranteBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Restaurante;

@RestController
@RequestMapping(Constantes.URL_BASE_RESTAURANTES)
public class RestauranteRestController {

	@Autowired
	private IRestauranteBusiness restauranteBusiness;

	@GetMapping("")
	public ResponseEntity<List<Restaurante>> list() {
		try {
			return new ResponseEntity<List<Restaurante>>(restauranteBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> load(@PathVariable("id") int idRestaurante) {
		try {
			return new ResponseEntity<Restaurante>(restauranteBusiness.load(idRestaurante), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/direccion")
	public ResponseEntity<Restaurante> loadByNombre(@RequestParam("nam") String nombre) {
		try {
			return new ResponseEntity<Restaurante>(restauranteBusiness.findByNombre(nombre), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/TheBestOne")
	public ResponseEntity<Restaurante> loadByOrderByPuntuacionDesc() {
		try {
			return new ResponseEntity<Restaurante>(restauranteBusiness.findFirstByOrderByPuntuacionDesc(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/TheBest")
	public ResponseEntity<List<Restaurante>> loadByPuntuacion() {
		try {
			return new ResponseEntity<List<Restaurante>>(restauranteBusiness.findByPuntuacion(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/food")
	public ResponseEntity<List<Restaurante>> loadByComidaNombreLike(@RequestParam("nam") String nombreComida) {
		try {
			return new ResponseEntity<List<Restaurante>>(restauranteBusiness.findByListadecomidasNombreLike(nombreComida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Restaurante restaurante) {
		try {
			restauranteBusiness.save(restaurante);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_RESTAURANTES	 + "/" + restaurante.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Restaurante restaurante) {
		try {
			restauranteBusiness.save(restaurante);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idRestaurante) {
		try {
			restauranteBusiness.remove(idRestaurante);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/horarioentre")
	public ResponseEntity<List<Restaurante>> loadByHorariodeApertura(@RequestParam("hora") String hora) {
		try {
			return new ResponseEntity<List<Restaurante>>(restauranteBusiness.findByHorariodeApertura(hora), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
