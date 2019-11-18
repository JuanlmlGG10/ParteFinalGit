package pe.edu.upc.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.DistributedManager;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

public interface IReservaService {

	public boolean insertar(Reserva reserva);
	public boolean modificar(Reserva reserva);
	public void eliminar(int idReserva);
	public Optional<Reserva> listarId(int idReserva);
	List<Reserva> listar();
	List<Reserva> buscarPersona(String nombrePersona);
	List<Reserva> buscarCancha(int idCancha);
	List<Reserva> buscarPorFecha(Date fechaInicio,Date fechaFin);
	List<Reserva> buscarPersonaid(int idPersona);
	List<Reserva> buscarPorUserName(String username);
}