package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;

@Repository
public interface IPersonaDAO extends JpaRepository<Persona, Integer>
{
	@Query("from Persona d where d.nombrePersona like %:nombrePersona%")
	List<Persona> buscarNombre(@Param("nombrePersona") String nombrePersona);
	
	@Query("from Persona d where d.distrito.idDistrito=:idDistrito")
	List<Persona> buscarDistrito(@Param("idDistrito")int idDistrito);
}
