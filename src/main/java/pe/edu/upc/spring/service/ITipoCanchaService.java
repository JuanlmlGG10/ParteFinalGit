package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

public interface ITipoCanchaService {

	public boolean insertar(TipoCancha tipoCancha);
	public boolean modificar(TipoCancha tipoCancha);
	public void eliminar(int idTipoCancha);
	public Optional<TipoCancha> listarId(int idTipoCancha);
	List<TipoCancha> listar();
	List<TipoCancha> buscarNombre(String nombreTipoCancha);
	
}