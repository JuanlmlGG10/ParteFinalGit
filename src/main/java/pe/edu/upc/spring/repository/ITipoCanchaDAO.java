package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.TipoCancha;

@Repository
public interface ITipoCanchaDAO extends JpaRepository<TipoCancha, Integer>
{
	@Query("from TipoCancha d where d.nombreTipoCancha like %:nombreTipoCancha%")
	List<TipoCancha> buscarNombre(@Param("nombreTipoCancha") String nombreTipoCancha);
	
}
