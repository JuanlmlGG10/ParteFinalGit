package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

@Repository
public interface ICanchaDAO extends JpaRepository<Cancha, Integer>
{
	@Query("from Cancha d where d.nombreCancha like %:nombreCancha%")
	List<Cancha> buscarNombre(@Param("nombreCancha") String nombreCancha);
	@Query("from Cancha d where d.tipoCancha.idTipoCancha like %:idTipoCancha%")
	List<Cancha> buscarTipoCancha(@Param("idTipoCancha") int idTipoCancha);
}
