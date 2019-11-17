package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;

public interface ISedeService {

	public boolean insertar(Sede sede);
	public boolean modificar(Sede sede);
	public void eliminar(int idSede);
	public Optional<Sede> listarId(int idSede);
	List<Sede> listar();
	List<Sede> buscarNombre(String nombreSede);
	
}