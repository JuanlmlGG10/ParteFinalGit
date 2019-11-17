package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController implements CommandLineRunner{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/*@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	@Autowired
	private IPersonaService pService;
	
	@Autowired
	private IDistritoService dService;
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irPersona(Map<String, Object> model) {
		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaDistritos", dService.listar());
		return "persona";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Persona objPersona, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		
		String bcryptPassword = null;
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			return "persona";
		}
		else {
			String usuarioo="Cliente";
			objPersona.setTipoUsuario(usuarioo);
			for (int i = 0; i < 2; i++) {
				bcryptPassword = passwordEncoder.encode(objPersona.getPassword());
				
			}
			objPersona.setPassword(bcryptPassword);
		
			
			boolean flag = pService.insertar(objPersona);
			if (flag) {
				return "redirect:/persona/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/persona/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Persona objPersona, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/persona/listar";
		}
		else {
			boolean flag = pService.modificar(objPersona);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/persona/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/persona/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Persona> objPersona = pService.listarId(id);
						
		if (objPersona == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/persona/listar";
		}
		else {
			
						
			model.addAttribute("listaDistritos", dService.listar());
			if (objPersona.isPresent())
				objPersona.ifPresent(o -> model.addAttribute("persona", o));			

			return "persona";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPersonas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar una persona asignada a una reserva");
			model.put("listaPersonas", pService.listar());
		}
		return "listPersona";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Persona persona) throws ParseException {
		pService.listarId(persona.getIdPersona());
		return "listPersona";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Persona persona,Model model1) throws ParseException {
		List<Persona> listaPersonas;
		persona.setNombrePersona(persona.getNombrePersona());
		
		
		 if(persona.getNombrePersona()!="") {
			listaPersonas = pService.buscarNombre(persona.getNombrePersona());	
			if (listaPersonas.isEmpty()) {
				model.put("mensaje", "No se encontro");		
			}
		}
		
		else  {
			
			listaPersonas=pService.buscarDistrito(persona.getDistrito().getIdDistrito());	
			if (listaPersonas.isEmpty()) {
				model.put("mensaje", "No se encontro");		
			}
		
		}
		
		model.put("listaPersonas", listaPersonas);
		model1.addAttribute("listaDistritos", dService.listar());
		return "buscarPersona";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaDistritos", dService.listar());
		return "buscarPersona";
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
		
}



