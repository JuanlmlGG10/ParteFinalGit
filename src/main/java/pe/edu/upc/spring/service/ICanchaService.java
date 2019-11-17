package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

public interface ICanchaService {

	public boolean insertar(Cancha cancha);
	public boolean modificar(Cancha cancha);
	public void eliminar(int idCancha);
	public Optional<Cancha> listarId(int idCancha);
	List<Cancha> listar();
	List<Cancha> listarportipocancha(int id);
	List<Cancha> buscarNombre(String nombreCancha);
	
}