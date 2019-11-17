package pe.edu.upc.spring.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.repository.IReservaDAO;
import pe.edu.upc.spring.service.IReservaService;
@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaDAO dReserva;

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public boolean insertar(Reserva reserva) {
		/*//float costo = reserva.getCancha().getTipoCancha().getSPrecioHora();
		int t=reserva.getCancha().getTipoCancha().getSPrecioHora();
		
		int tiempo=reserva.getHoraFin().getHours()-reserva.getHoraInicio().getHours();
		//reserva.setMontoReserva(costo * tiempo);*/
		Reserva objReserva = dReserva.save(reserva);
		if (objReserva == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Reserva reserva) {
		boolean flag = false;
		try {
			dReserva.save(reserva);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idReserva) {

		dReserva.deleteById(idReserva);

	}

	@Override
	public Optional<Reserva> listarId(int idReserva) {
		return dReserva.findById(idReserva);		
	}

	
	@Override
	public List<Reserva> listar() {

		return dReserva.findAll();

	}

	@Override
	public List<Reserva> buscarPersona(String nombrePersona) {
		// TODO Auto-generated method stub
		return dReserva.buscarPersona(nombrePersona);
	}
	
	@Override
	public List<Reserva> buscarCancha(int idCancha){
		return dReserva.buscarCancha(idCancha);
		
	}
	@Override
	public List<Reserva> buscarPorFecha(Date fechaInicio,Date fechaFin){
	return dReserva.buscarByFecha(fechaInicio, fechaFin);
	}
	@Override
	public List<Reserva> buscarPersonaid(int idPersona){
		return dReserva.buscarPersonaid(idPersona);
		
	}
	
}
