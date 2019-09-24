package com.example.demo.business;

import java.util.List;

import com.example.demo.model.Comida;



public interface IComidaBusiness {
	
public List<Comida> list() throws BusinessException;
	
	public Comida load(int idComida) throws BusinessException, NotFoundException;

	public Comida save(Comida comida) throws BusinessException;
	
	public void remove(int idComida) throws BusinessException, NotFoundException;
	
	public List<Comida> findByRestauranteNombre(String nombreRestaurante)throws BusinessException, NotFoundException;

	public Comida findFirstByOrderByPrecioAsc() throws BusinessException, NotFoundException;
	
	public Comida findFirstByOrderByPrecioDesc()throws BusinessException, NotFoundException;
	
	public Comida findFirstByRestauranteNombreOrderByPrecioAsc(String nombreRestaurante)throws BusinessException, NotFoundException;
	
	public Comida findFirstByRestauranteNombreOrderByPrecioDesc(String nombreRestaurante)throws BusinessException, NotFoundException;
}
