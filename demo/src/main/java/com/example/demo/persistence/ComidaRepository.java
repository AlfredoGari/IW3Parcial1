package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {
	
	public List<Comida> findByRestauranteNombre(String nombreRestaurante);
	
	public Comida findFirstByOrderByPrecioAsc();
	
	public Comida findFirstByOrderByPrecioDesc();
	
	public Comida findFirstByRestauranteNombreOrderByPrecioAsc(String nombreRestaurante);
	
	public Comida findFirstByRestauranteNombreOrderByPrecioDesc(String nombreRestaurante);
}
