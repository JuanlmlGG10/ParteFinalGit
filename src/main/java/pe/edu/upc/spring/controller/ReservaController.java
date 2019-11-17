package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.model.TipoCancha;
import pe.edu.upc.spring.service.ICanchaService;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.IReservaService;
import pe.edu.upc.spring.service.ISedeService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService rService;
	
	@Autowired
	private ICanchaService cService;
	
	@Autowired
	private ISedeService sService;

	@Autowired
	private IPersonaService pService;
	
	private Date fechaInicio;
	private Date fechaFin;
	
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irReserva(Map<String, Object> model) {
		model.put("listaReservas", rService.listar());
		return "listReserva";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("listaCanchas", cService.listar());
		model.addAttribute("listaPersonas", pService.listar());
		return "reserva";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Reserva objReserva, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaCanchas", cService.listar());
			model.addAttribute("listaPersonas", pService.listar());
			return "reserva";
		}
		else {		
			int tiempo=objReserva.getHoraFin().getHours()-objReserva.getHoraInicio().getHours();
			//double costo= objReserva.getCancha().getTipoCancha().getPrecioHora();
			//Cancha cancha=objReserva.getCancha();
			//Sede sede= cancha.getSede();
			//Distrito distrito = sede.getDistrito();
			//String nombredistrito = distrito.getNombreDistrito();
			//double costo=objReserva.getCancha().getTipoCancha().getPrecioHora();
			
			
			//TipoCancha tipocancha=cancha.getTipoCancha();
			//double costo=tipocancha.getIdTipoCancha();
			//objReserva.setMontoReserva(tiempo*(objReserva.getCancha().getTipoCancha().getPrecioHora()));
			double costo = 0;
			int id=objReserva.getCancha().getIdCancha();
			List<Cancha> canchaseleccionada= cService.listar();
			/*for (int i = 0; i < canchaseleccionada.size(); i++) {
				costo=canchaseleccionada.get(i).getTipoCancha().getPrecioHora();
			}*/
			Cancha canchaaaaaa = null;
			for (int i = 0; i < canchaseleccionada.size(); i++) {
				if(canchaseleccionada.get(i).getIdCancha()==objReserva.getCancha().getIdCancha()) {
					canchaaaaaa=canchaseleccionada.get(i);
					break;
				}
			}
			costo=canchaaaaaa.getTipoCancha().getPrecioHora();
			
			objReserva.setMontoReserva(costo*tiempo);
			boolean flag = rService.insertar(objReserva);
			if (flag) {
				return "redirect:/reserva/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/reserva/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Reserva objReserva, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/reserva/listar";
		}
		else {
			boolean flag = rService.modificar(objReserva);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/reserva/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/reserva/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Reserva> objReserva = rService.listarId(id);
						
		if (objReserva == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/reserva/listar";
		}
		else {
			
						
			model.addAttribute("listaCanchas", cService.listar());
			model.addAttribute("listaPersonas", pService.listar());
			if (objReserva.isPresent())
				objReserva.ifPresent(o -> model.addAttribute("reserva", o));			

			return "reserva";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaReservas", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar esta reserva");
			model.put("listaReservas", rService.listar());
		}
		return "listReserva";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaReservas", rService.listar());
		return "listReserva";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Reserva reserva) throws ParseException {
		rService.listarId(reserva.getIdReserva());
		return "listReserva";		
	}
	
	public String FiltrarCanchas(Map<String, Object> model,int id) {
		model.put("listaCanchas", cService.listarportipocancha(id));
		return "listReserva";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Reserva reserva,Model model1) throws ParseException {
		List<Reserva> listaReservas;
		//sede.setNombreSede(sede.getNombreSede());
		
		listaReservas= rService.buscarPersonaid(reserva.getPersona().getIdPersona());		
		if (listaReservas.isEmpty()) {
			listaReservas=rService.buscarCancha(reserva.getCancha().getIdCancha());
			if (listaReservas.isEmpty()) {
			model.put("mensaje", "No se encontro");}						
		}
		model.put("listaReservas", listaReservas);
		model1.addAttribute("listaPersonas", pService.listar());
		model1.addAttribute("listaCanchas", cService.listar());
		return "buscarReserva";		
	}
	
	@RequestMapping("/buscar1")
	public String buscar1(Map<String, Object> model, @ModelAttribute Reserva reserva,Model model1) throws ParseException {
		List<Reserva> listaReservas;
		//sede.setNombreSede(sede.getNombreSede());
		listaReservas=rService.buscarCancha(reserva.getCancha().getIdCancha());
	
		if (listaReservas.isEmpty()) {
			model.put("mensaje", "No se encontro");
			}						
		
		model.put("listaReservas1", listaReservas);
		model1.addAttribute("listaPersonas", pService.listar());
		model1.addAttribute("listaCanchas", cService.listar());
		return "buscarReserva";		
	}
	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("listaPersonas", pService.listar());
		model.addAttribute("listaCanchas", cService.listar());
		return "buscarReserva";
	}
	
	
/*	@RequestMapping("/buscar1")
	public String buscar2(Map<String, Object> model, @ModelAttribute Reserva reserva,Model model1) throws ParseException {
		List<Reserva> listaReservas;
		//sede.setNombreSede(sede.getNombreSede());
		listaReservas=rService.buscarporLibro(reserva);
	
		if (listaReservas.isEmpty()) {
			model.put("mensaje", "No se encontro");
			}						
		
		model.put("listaReservas1", listaReservas);
		model1.addAttribute("listaPersonas", pService.listar());
		model1.addAttribute("listaCanchas", cService.listar());
		return "buscarReserva";		
	}*/
}






