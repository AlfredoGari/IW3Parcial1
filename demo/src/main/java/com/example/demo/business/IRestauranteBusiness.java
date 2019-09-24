package com.example.demo.business;


import java.util.List;


import com.example.demo.model.Restaurante;

public interface IRestauranteBusiness {

	public List<Restaurante> list() throws BusinessException;
	
	public Restaurante load(int idRestaurante) throws BusinessException, NotFoundException;

	public Restaurante save(Restaurante restaurante) throws BusinessException;
	
	public void remove(int idRestaurante) throws BusinessException, NotFoundException;
	
	public Restaurante findByNombre(String nombreRestaurante) throws BusinessException, NotFoundException;

	public Restaurante findFirstByOrderByPuntuacionDesc()throws BusinessException, NotFoundException;
	
	public List<Restaurante> findByPuntuacion()throws BusinessException, NotFoundException;
	
	public List<Restaurante> findByListadecomidasNombreLike(String nombreComida)throws BusinessException, NotFoundException;

	public List<Restaurante> findByHorariodeApertura(String hora)throws BusinessException, NotFoundException;
}
