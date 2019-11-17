package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Reserva;

@Repository
public interface IReservaDAO extends JpaRepository<Reserva, Integer>
{
	/*@Query("from Reserva p where p.sede.nombreSede like %:nombreSede%")
	List<Reserva> buscarSede(@Param("nombreSede") String nombreSede);
	*/
	@Query("from Reserva r where r.persona.nombrePersona like %:nombrePersona%")
	List<Reserva> buscarPersona(@Param("nombrePersona") String nombrePersona);
	
	@Query("from Reserva r where r.cancha.idCancha=:idCancha")
	List<Reserva> buscarCancha(@Param("idCancha") int idCancha);
	
	@Query("from Reserva r where r.fechaReserva between :fechaInicio and :fechaFin")
	List<Reserva> buscarByFecha(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
	
	@Query("from Reserva r where r.persona.idPersona=:idPersona")
	List<Reserva> buscarPersonaid(@Param("idPersona")int idPersona);
	/*@Query("from Reserva r where r.ejemplar.libro.idLibro=:idLibro")
	List<Reserva> buscarPorLibro(@Param("idLibro") int idLibro);*/
}
