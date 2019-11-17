package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.service.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService {

	@Autowired
	private IDistritoDAO dDistrito;

	@Override
	@Transactional
	public boolean insertar(Distrito distrito) {
		Distrito objDistrito= dDistrito.save(distrito);
		if (objDistrito == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Distrito distrito) {
		boolean flag = false;
		try {
			dDistrito.save(distrito);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDistrito) {

		dDistrito.deleteById(idDistrito);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Distrito> listarId(int idDistrito) {
		return dDistrito.findById(idDistrito);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Distrito> buscarNombre(String nombreDistrito) {

		return dDistrito.buscarNombre(nombreDistrito);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Distrito> listar() {
		return dDistrito.findAll();
	}

}
