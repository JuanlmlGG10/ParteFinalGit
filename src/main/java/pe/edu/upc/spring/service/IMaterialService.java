package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Material;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;

public interface IMaterialService {

	public boolean insertar(Material material);
	public boolean modificar(Material material);
	public void eliminar(int idMaterial);
	public Optional<Material> listarId(int idMaterial);
	List<Material> listar();
	List<Material> buscarNombre(String nombreMaterial);
	
}