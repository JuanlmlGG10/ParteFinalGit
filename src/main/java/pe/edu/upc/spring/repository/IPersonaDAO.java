package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Usuario;

@Repository
public interface IPersonaDAO extends JpaRepository<Persona, Integer>
{
	@Query("from Persona d where d.username like :username")
	List<Persona> buscarNombre(@Param("username") String username);
	
	@Query("from Persona d where d.distrito.idDistrito=:idDistrito")
	List<Persona> buscarDistrito(@Param("idDistrito")int idDistrito);
	public Persona findByUsername(String username);
	
	@Query("from Persona r where r.nombrePersona like %:nombrePersona%")
	List<Persona> buscarNombreReal(@Param("nombrePersona") String nombrePersona);
}
