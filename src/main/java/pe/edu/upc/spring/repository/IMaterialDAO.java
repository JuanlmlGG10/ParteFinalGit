package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Material;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;

@Repository
public interface IMaterialDAO extends JpaRepository<Material, Integer>
{
	@Query("from Material d where d.nombreMaterial like %:nombreMaterial%")
	List<Material> buscarMaterial(@Param("nombreMaterial") String nombreMaterial);
}
