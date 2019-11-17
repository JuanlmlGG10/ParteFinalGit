package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Kit;
import pe.edu.upc.spring.model.Material;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;

public interface IKitService {

	public boolean insertar(Kit kit);
	public boolean modificar(Kit kit);
	public void eliminar(int idKit);
	public Optional<Kit> listarId(int idKit);
	List<Kit> listar();
	List<Kit> buscarNombre(String nombreKit);
	
}