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
import pe.edu.upc.spring.service.IDistritoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private IDistritoService dService;
	
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irDistrito() {
		
		return "bienvenido";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("distrito", new Distrito());
		return "distrito";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Distrito objDistrito, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "distrito";
		}
		else {
			boolean flag = dService.insertar(objDistrito);
			if (flag) {
				return "redirect:/distrito/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/distrito/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Distrito objDistrito, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/distrito/listar";
		}
		else {
			boolean flag = dService.modificar(objDistrito);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/distrito/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/distrito/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Distrito> objDistrito = dService.listarId(id);
		if (objDistrito == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/distrito/listar";
		}
		else {
			model.addAttribute("distrito", objDistrito);
			return "distrito";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDistritos", dService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un distrito asignado");
			model.put("listaDistritos", dService.listar());
		}
		return "listDistrito";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDistritos", dService.listar());
		return "listDistrito";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Distrito distrito) throws ParseException {
		dService.listarId(distrito.getIdDistrito());
		return "listDistrito";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Distrito distrito) throws ParseException {
		List<Distrito> listaDistritos;
		distrito.setNombreDistrito(distrito.getNombreDistrito());
		listaDistritos= dService.buscarNombre(distrito.getNombreDistrito());
		
		if (listaDistritos.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaDistritos", listaDistritos);
		return "buscarDistrito";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("distrito", new Distrito());
		return "buscarDistrito";
	}
		
}



