package pe.edu.upc.spring.controller;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

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

import pe.edu.upc.spring.model.Cancha;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.service.ICanchaService;
import pe.edu.upc.spring.service.ISedeService;
import pe.edu.upc.spring.service.ITipoCanchaService;

@Controller
@RequestMapping("/cancha")
public class CanchaController {
	
	@Autowired
	private ICanchaService cService;
	
	@Autowired
	private ISedeService sService;
	
	@Autowired
	private ITipoCanchaService tcService;	
	
	
	@RequestMapping("/")
	public String irCancha(Map<String, Object> model) {
		model.put("listaCanchas", cService.listar());
		return "listCancha";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("cancha", new Cancha());
		model.addAttribute("listaSedes", sService.listar());
		model.addAttribute("listaTipoCanchas", tcService.listar());
		return "cancha";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Cancha objCancha, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaSedes", sService.listar());
			model.addAttribute("listaTipoCanchas", tcService.listar());
			return "cancha";
		}
		else {
			boolean flag = cService.insertar(objCancha);
			if (flag) {
				return "redirect:/cancha/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cancha/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Cancha objCancha, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/cancha/listar";
		}
		else {
			boolean flag = cService.modificar(objCancha);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/cancha/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cancha/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Cancha> objCancha = cService.listarId(id);
						
		if (objCancha == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/cancha/listar";
		}
		else {
			
						
			model.addAttribute("listaSedes", sService.listar());
			model.addAttribute("listaTipoCanchas", tcService.listar());
			if (objCancha.isPresent())
				objCancha.ifPresent(o -> model.addAttribute("cancha", o));			

			return "cancha";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
		if (id!=null && id>0) {
			cService.eliminar(id);
			model.put("listaCanchas", cService.listar());
		}
		}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
		model.put("mensaje", "No se puede eliminar una cancha asignada");
		model.put("listaCanchas", cService.listar());
	}
	
		return "listCancha";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCanchas", cService.listar());
		return "listCancha";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Cancha cancha) throws ParseException {
		cService.listarId(cancha.getIdCancha());
		return "listCancha";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Cancha cancha) throws ParseException {
		List<Cancha> listaCanchas;
		cancha.setNombreCancha(cancha.getNombreCancha());
		listaCanchas= cService.buscarNombre(cancha.getNombreCancha());
		
		if (listaCanchas.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaCanchas", listaCanchas);
		return "buscarCancha";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("cancha", new Cancha());
		return "buscarCancha";
	}
	
}

