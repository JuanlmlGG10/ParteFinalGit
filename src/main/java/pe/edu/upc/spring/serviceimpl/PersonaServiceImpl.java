package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDAO dPersona;

	@Override
	@Transactional
	public boolean insertar(Persona persona) {
		Persona objPersona = dPersona.save(persona);
		if (objPersona == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Persona persona) {
		boolean flag = false;
		try {
			dPersona.save(persona);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPersona) {

		dPersona.deleteById(idPersona);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Persona> listarId(int idPersona) {
		return dPersona.findById(idPersona);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Persona> buscarNombre(String username) {

		return dPersona.buscarNombre(username);

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Persona> buscarDistrito(int idDistrito) {

		return dPersona.buscarDistrito(idDistrito);

	}
	

	@Override
	@Transactional(readOnly=true)
	public List<Persona> listar() {
		return dPersona.findAll();
	}

}
