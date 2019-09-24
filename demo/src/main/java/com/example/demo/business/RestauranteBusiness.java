package com.example.demo.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Restaurante;
import com.example.demo.persistence.RestauranteRepository;

@Service
public class RestauranteBusiness implements IRestauranteBusiness {
	private Logger log = LoggerFactory.getLogger(RestauranteBusiness.class.getName());
	@Autowired
	private RestauranteRepository restauranteDAO;
	
	public RestauranteBusiness() {
		
	}

	@Override
	public List<Restaurante> list() throws BusinessException {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
			return restauranteDAO.findAll();
		} catch (Exception e) {
			log.error("["+stackTraceElements[2] + "]"  + "[" +stackTraceElements[1]+ "]"+ e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Restaurante load(int idRestaurante) throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;
		try {
			op = restauranteDAO.findById(idRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el restaurante con id=" + idRestaurante);
		return op.get();
	}

	@Override
	public Restaurante save(Restaurante restaurante) throws BusinessException {
		try {
			return restauranteDAO.save(restaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(int idRestaurante) throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;

		try {
			op = restauranteDAO.findById(idRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el restaurante con id=" + idRestaurante);
		try {
			restauranteDAO.deleteById(idRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public Restaurante findByNombre(String nombreRestaurante) throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;
		try {
			op = restauranteDAO.findByNombre(nombreRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el restaurante con nombre=" + nombreRestaurante);
		return op.get();
	}

	@Override
	public Restaurante findFirstByOrderByPuntuacionDesc() throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;
		try {
			op = restauranteDAO.findFirstByOrderByPuntuacionDesc();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("");
		return op.get();
	}

	@Override
	public List<Restaurante> findByPuntuacion() throws BusinessException, NotFoundException {
		Restaurante op = null;
		List<Restaurante> opp;
		try {
			op = restauranteDAO.findFirstByOrderByPuntuacionDesc().get();
			opp= restauranteDAO.findByPuntuacion(op.getPuntuacion());
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return opp;
	}

	@Override
	public List<Restaurante> findByListadecomidasNombreLike(String nombreComida) throws BusinessException, NotFoundException {
		List<Restaurante> opp;
		try {
			opp=restauranteDAO.findByListadecomidasNombreLike("%"+nombreComida+"%");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return opp;
	}

	@Override
	public List<Restaurante> findByHorariodeApertura(String hora) throws BusinessException, NotFoundException {
		List<Restaurante> list = new ArrayList<>();
		try {
			
			LocalTime localtime = LocalTime.parse(hora);
			for(Restaurante r : restauranteDAO.findAll()) {
				System.out.println(r.getNombre());
                if (localtime.isAfter(r.getHorariodeApertura()) && localtime.isBefore(r.getHorariodeSalida())){
                	list.add(r);
                }
                
                if(r.getHorariodeApertura().isAfter(r.getHorariodeSalida()) && !(r.getHorariodeApertura().isAfter(localtime) && localtime.isAfter(r.getHorariodeSalida()))){
                	list.add(r);
            	}           
             
		}
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		
		return list;
	}

	
}
