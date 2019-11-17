package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

@Repository
public interface ISedeDAO extends JpaRepository<Sede, Integer>
{
	@Query("from Sede d where d.nombreSede like %:nombreSede%")
	List<Sede> buscarNombre(@Param("nombreSede") String nombreSede);
}
