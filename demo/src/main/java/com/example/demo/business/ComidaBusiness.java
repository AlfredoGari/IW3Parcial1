package com.example.demo.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comida;
import com.example.demo.persistence.ComidaRepository;

@Service
public class ComidaBusiness implements IComidaBusiness {
	private Logger log = LoggerFactory.getLogger(ComidaBusiness.class.getName());
	@Autowired
	private ComidaRepository comidaDAO;
	
	public ComidaBusiness() {
		
	}

	@Override
	public List<Comida> list() throws BusinessException {
		 StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			 log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "] [ " + comidaDAO.findAll().toString() + " ]" );
			return comidaDAO.findAll();
		} catch (Exception e) {
			log.error("["+stackTraceElements[2] + "]"  + "[" +stackTraceElements[1]+ "]"+ e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Comida load(int idComida) throws BusinessException, NotFoundException {
		 StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		Optional<Comida> op = null;
		try {
			op = comidaDAO.findById(idComida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la comida con id=" + idComida);
		}
		
		log.info("["+stackTraceElements[2] + "]"  + "[" +stackTraceElements[1]+ "]" + "se encontro la comida: "+op.get().toString());
		return op.get();
			
	}

	@Override
	public Comida save(Comida comida) throws BusinessException {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			 log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
			return comidaDAO.save(comida);
		} catch (Exception e) {
			log.error("["+stackTraceElements[2] + "]"  + "[" +stackTraceElements[1]+ "]"+ e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(int idComida) throws BusinessException, NotFoundException {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		Optional<Comida> op = null;

		try {
			op = comidaDAO.findById(idComida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra la comida con id=" + idComida);
		try {
			comidaDAO.deleteById(idComida);
			log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		} catch (Exception e) {
			log.error("["+stackTraceElements[2] + "]"  + "[" +stackTraceElements[1]+ "]"+ e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<Comida> findByRestauranteNombre(String nombreRestaurante) throws BusinessException, NotFoundException {
		List<Comida> op;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			op = comidaDAO.findByRestauranteNombre(nombreRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		return op;
	}

	@Override
	public Comida findFirstByOrderByPrecioAsc() throws BusinessException, NotFoundException {
		Comida op = null;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			op = comidaDAO.findFirstByOrderByPrecioAsc();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		return op;
	}

	@Override
	public Comida findFirstByOrderByPrecioDesc() throws BusinessException, NotFoundException {
		Comida op = null;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			op = comidaDAO.findFirstByOrderByPrecioDesc();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		return op;
	}

	@Override
	public Comida findFirstByRestauranteNombreOrderByPrecioAsc(String nombreRestaurante)
			throws BusinessException, NotFoundException {
		Comida op = null;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		try {
			op = comidaDAO.findFirstByRestauranteNombreOrderByPrecioAsc(nombreRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		return op;
	}

	@Override
	public Comida findFirstByRestauranteNombreOrderByPrecioDesc(String nombreRestaurante)
			throws BusinessException, NotFoundException {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		Comida op = null;
		try {
			op = comidaDAO.findFirstByRestauranteNombreOrderByPrecioDesc(nombreRestaurante);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		log.info("[" + stackTraceElements[2] + "] ["+stackTraceElements[1] + "]");
		return op;
	}

	

}
