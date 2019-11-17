package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;

public interface IPersonaService {

	public boolean insertar(Persona persona);
	public boolean modificar(Persona persona);
	public void eliminar(int idPersona);
	public Optional<Persona> listarId(int idPersona);
	List<Persona> listar();
	List<Persona> buscarNombre(String username);
	List<Persona> buscarDistrito(int idDistrito);
}