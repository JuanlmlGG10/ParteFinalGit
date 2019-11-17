package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Kit;
import pe.edu.upc.spring.model.Persona;

@Repository
public interface IKitDAO extends JpaRepository<Kit, Integer>
{
	@Query("from Kit d where d.nombreKit like %:nombreKit%")
	List<Kit> buscarNombre(@Param("nombreKit") String nombreKit);
}
