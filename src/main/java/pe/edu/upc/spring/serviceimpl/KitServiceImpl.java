package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Kit;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IKitDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.repository.ISedeDAO;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IKitService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;

@Service
public class KitServiceImpl implements IKitService {

	@Autowired
	private IKitDAO dKit;

	@Override
	@Transactional
	public boolean insertar(Kit kit) {
		Kit objKit= dKit.save(kit);
		if (objKit == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Kit kit) {
		boolean flag = false;
		try {
			dKit.save(kit);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idKit) {

		dKit.deleteById(idKit);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Kit> listarId(int idKit) {
		return dKit.findById(idKit);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Kit> buscarNombre(String nombreKit) {

		return dKit.buscarNombre(nombreKit);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Kit> listar() {
		return dKit.findAll();
	}

}
