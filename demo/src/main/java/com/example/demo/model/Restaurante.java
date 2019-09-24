package com.example.demo.model;



import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="restaurantes")
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100)
	private String nombre;
	private String direccion;
	@Column(name = "horariode_apertura")
	private LocalTime horariodeApertura;
	@Column(name = "horariode_asalida")
	private LocalTime horariodeSalida;
	private double puntuacion;
	@OneToMany(mappedBy = "restaurante")
	@JsonManagedReference
	private List<Comida> listadecomidas;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public LocalTime getHorariodeApertura() {
		return horariodeApertura;
	}
	public void setHorariodeApertura(LocalTime horariodeApertura) {
		this.horariodeApertura = horariodeApertura;
	}
	public LocalTime getHorariodeSalida() {
		return horariodeSalida;
	}
	public void setHorariodeSalida(LocalTime horariodeSalida) {
		this.horariodeSalida = horariodeSalida;
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public List<Comida> getListadecomidas() {
		return listadecomidas;
	}
	public void setListadecomidas(List<Comida> listadecomidas) {
		this.listadecomidas = listadecomidas;
	}
	
	
	

}
