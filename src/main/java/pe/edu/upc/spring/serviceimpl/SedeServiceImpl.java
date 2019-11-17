package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.repository.ISedeDAO;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;

@Service
public class SedeServiceImpl implements ISedeService {

	@Autowired
	private ISedeDAO dSede;

	@Override
	@Transactional
	public boolean insertar(Sede sede) {
		Sede objSede = dSede.save(sede);
		if (objSede == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Sede sede) {
		boolean flag = false;
		try {
			dSede.save(sede);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idSede) {

		dSede.deleteById(idSede);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Sede> listarId(int idSede) {
		return dSede.findById(idSede);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Sede> buscarNombre(String nombreSede) {

		return dSede.buscarNombre(nombreSede);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Sede> listar() {
		return dSede.findAll();
	}

}
