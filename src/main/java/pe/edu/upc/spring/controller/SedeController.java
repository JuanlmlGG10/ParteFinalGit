package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Sede;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ISedeService;

@Controller
@RequestMapping("/sede")
public class SedeController {
	
	@Autowired
	private ISedeService sService;
	
	@Autowired
	private IDistritoService dService;
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irSede(Map<String, Object> model) {
		model.put("listaSedes", sService.listar());
		return "listSede";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("sede", new Sede());
		model.addAttribute("listaDistritos", dService.listar());
		return "sede";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Sede objSede, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			return "sede";
		}
		else {		
			boolean flag = sService.insertar(objSede);
			if (flag) {
				return "redirect:/sede/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/sede/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Sede objSede, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/sede/listar";
		}
		else {
			boolean flag = sService.modificar(objSede);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/sede/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/sede/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Sede> objSede = sService.listarId(id);
						
		if (objSede == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/sede/listar";
		}
		else {
			
						
			model.addAttribute("listaDistritos", dService.listar());
			if (objSede.isPresent())
				objSede.ifPresent(o -> model.addAttribute("sede", o));			

			return "sede";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				sService.eliminar(id);
				model.put("listaSedes", sService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar una sede asignada a una cancha");
			model.put("listaSedes", sService.listar());
		}
		return "listSede";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSedes", sService.listar());
		return "listSede";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Sede sede) throws ParseException {
		sService.listarId(sede.getIdSede());
		return "listSede";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Sede sede) throws ParseException {
		List<Sede> listaSedes;
		sede.setNombreSede(sede.getNombreSede());
		listaSedes = sService.buscarNombre(sede.getNombreSede());
		
		if (listaSedes.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaSedes", listaSedes);
		return "buscarSede";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("sede", new Sede());
		return "buscarSede";
	}
		
}



