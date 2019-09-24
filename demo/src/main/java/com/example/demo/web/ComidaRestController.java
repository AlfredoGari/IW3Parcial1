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
import com.example.demo.business.IComidaBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Comida;

@RestController
@RequestMapping(Constantes.URL_BASE_COMIDAS)
public class ComidaRestController {
	
	@Autowired
	private IComidaBusiness comidaBusiness;
	
	@GetMapping("")
	public ResponseEntity<List<Comida>> list() {
		try {
			return new ResponseEntity<List<Comida>>(comidaBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Comida>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comida> load(@PathVariable("id") int idComida) {
		try {
			return new ResponseEntity<Comida>(comidaBusiness.load(idComida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/resto")
	public ResponseEntity<List<Comida>> loadByRestaurante(@RequestParam("res") String nombreRestaurante) {
		try {
			return new ResponseEntity<List<Comida>>(comidaBusiness.findByRestauranteNombre(nombreRestaurante), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Comida>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Comida>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/MaMe")
	public ResponseEntity<Comida> loadByComidas(@RequestParam("pre") String precio, @RequestParam("res") String restaurante) {
	
		
		if(precio.equals("menor") && restaurante.equals("ALL")) {
			try {
				return new ResponseEntity<Comida>(comidaBusiness.findFirstByOrderByPrecioAsc(), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
			}
		}
		
		if(precio.equals("mayor") && restaurante.equals("ALL")) {
			try {
				return new ResponseEntity<Comida>(comidaBusiness.findFirstByOrderByPrecioDesc(), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
			}
		}
		
		if(precio.equals("menor")) {
			try {
				return new ResponseEntity<Comida>(comidaBusiness.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
			}
		}
		
		if(precio.equals("mayor")) {
			try {
				return new ResponseEntity<Comida>(comidaBusiness.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
			}
		}
		
		return null;
		
	}
	
	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Comida comida) {
		try {
			comidaBusiness.save(comida);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_COMIDAS + "/" + comida.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Comida comida) {
		try {
			comidaBusiness.save(comida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idComida) {
		try {
			comidaBusiness.remove(idComida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
