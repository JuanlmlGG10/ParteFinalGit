package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Kit;
import pe.edu.upc.spring.model.Material;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IKitDAO;
import pe.edu.upc.spring.repository.IMaterialDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.repository.ISedeDAO;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IKitService;
import pe.edu.upc.spring.service.IMaterialService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;

@Service
public class MaterialServiceImpl implements IMaterialService {

	@Autowired
	private IMaterialDAO dMaterial;

	@Override
	@Transactional
	public boolean insertar(Material material) {
		Material objMaterial= dMaterial.save(material);
		if (objMaterial == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Material material) {
		boolean flag = false;
		try {
			dMaterial.save(material);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idMaterial) {

		dMaterial.deleteById(idMaterial);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Material> listarId(int idMaterial) {
		return dMaterial.findById(idMaterial);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> buscarNombre(String nombreMaterial) {

		return dMaterial.buscarMaterial(nombreMaterial);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> listar() {
		return dMaterial.findAll();
	}

}
