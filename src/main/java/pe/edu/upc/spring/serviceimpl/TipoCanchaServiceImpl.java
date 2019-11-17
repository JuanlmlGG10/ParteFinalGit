package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;
import pe.edu.upc.spring.repository.IDistritoDAO;
import pe.edu.upc.spring.repository.IPersonaDAO;
import pe.edu.upc.spring.repository.ISedeDAO;
import pe.edu.upc.spring.repository.ITipoCanchaDAO;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;
import pe.edu.upc.spring.service.ITipoCanchaService;

@Service
public class TipoCanchaServiceImpl implements ITipoCanchaService {

	@Autowired
	private ITipoCanchaDAO dTipoCancha;

	@Override
	@Transactional
	public boolean insertar(TipoCancha tipoCancha) {
		TipoCancha objTipoCancha= dTipoCancha.save(tipoCancha);
		if (objTipoCancha == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(TipoCancha tipoCancha) {
		boolean flag = false;
		try {
			dTipoCancha.save(tipoCancha);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idTipoCancha) {

		dTipoCancha.deleteById(idTipoCancha);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<TipoCancha> listarId(int idTipoCancha) {
		return dTipoCancha.findById(idTipoCancha);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoCancha> buscarNombre(String nombreTipoCancha) {

		return dTipoCancha.buscarNombre(nombreTipoCancha);

	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoCancha> listar() {
		return dTipoCancha.findAll();
	}

}
