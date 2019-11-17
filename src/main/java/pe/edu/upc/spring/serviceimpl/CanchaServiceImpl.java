package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;
import pe.edu.upc.spring.repository.ICanchaDAO;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.repository.ISedeDAO;
import pe.edu.upc.spring.repository.ITipoCanchaDAO;
import pe.edu.upc.spring.service.ICanchaService;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;
import pe.edu.upc.spring.service.ITipoCanchaService;

@Service
public class CanchaServiceImpl implements ICanchaService {

	@Autowired
	private ICanchaDAO dCancha;

	@Override
	@Transactional
	public boolean insertar(Cancha cancha) {
		Cancha objCancha= dCancha.save(cancha);
		if (objCancha == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Cancha cancha) {
		boolean flag = false;
		try {
			dCancha.save(cancha);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCancha) {

		dCancha.deleteById(idCancha);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Cancha> listarId(int idCancha) {
		return dCancha.findById(idCancha);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cancha> buscarNombre(String nombreCancha) {

		return dCancha.buscarNombre(nombreCancha);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Cancha> listar() {
		return dCancha.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public List<Cancha> listarportipocancha(int id){
		return dCancha.buscarTipoCancha(id);
	}

}
