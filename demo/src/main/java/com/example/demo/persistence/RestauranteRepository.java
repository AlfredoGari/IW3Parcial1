package com.example.demo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

	Optional<Restaurante> findByNombre(String nombreRestaurante);

	Optional<Restaurante> findFirstByOrderByPuntuacionDesc();
	
	List<Restaurante> findByPuntuacion(double puntuacion);
	
	List<Restaurante> findByListadecomidasNombreLike(String nombreComida);
	
	
}
