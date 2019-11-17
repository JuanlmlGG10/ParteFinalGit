package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import antlr.debug.NewLineEvent;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;
import pe.edu.upc.spring.model.Role;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/*@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} 
		//Collection<? extends GrantedAuthority> tiporol=((UserDetails)principal).getAuthorities();
		
		List<Persona> tiporol=pService.buscarNombre(username);
		
		if(tiporol==null) {return "error";}
		Persona personalogeada=null;
		/*for (int i = 0; i < tiporol.size(); i++) {
			if(tiporol.get(i).getUsername()==username) {
				personalogeada=tiporol.get(i);
				
			}
		}*/
		
		/*for (int i = 0; i < personalogeada.getRoles().size(); i++) {
			
			if(personalogeada.getRoles().get(i).getAuthority()=="ROLE_USER")
			{model.put("listaPersonas", pService.buscarNombre(username));
			break;
			}
			if(personalogeada.getRoles().get(i).getAuthority()=="ROLE_ADMIN")
			{model.put("listaPersonas", pService.listar());
			break;
			
		
		}
			}*/
		
		String rooool=tiporol.get(0).getRoles().get(0).getAuthority();
		//if(Role==""||Role==null) {return "error";}
		
		System.out.println(rooool);
	
		if(rooool.equals("ROLE_ADMIN")) { model.put("listaPersonas", pService.listar());}
		else { 
			model.put("listaPersonas", pService.buscarNombre(username));
			}
		System.out.println(rooool);		
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
		
			Role nuevo=new Role();
			nuevo.setAuthority("ROLE_USER");
		 	objPersona.setRoles(Arrays.asList(nuevo));
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
			 System.out.println(persona.getNombrePersona());		
			listaPersonas = pService.buscarNombreReal(persona.getNombrePersona());	
			
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
	
	
		
}



