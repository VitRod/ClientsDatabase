package com.vit.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vit.dao.ClientDao;
import com.vit.model.Client;


@Controller
public class ClientsRegistrationController {
	
	@Autowired
	private ClientDao clientDao;
	

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String clientRegistration(ModelMap model) {
		Client client =  new  Client();
		model.addAttribute("client",  client);
		return "register";
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveRegistration(@Valid Client client,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "register";
		}
	
		clientDao.save(client);
		
		return "redirect:/viewclients";
	}
	
	@RequestMapping(value="/viewclients")
	public ModelAndView getAll() {
		
		List<Client> list = clientDao.findAll();
		return new ModelAndView("viewclients", "list", list);
	}
	
	
	@RequestMapping(value="/editclient/{id}")
	public String edit (@PathVariable int id, ModelMap model) {
		
		Client client = clientDao.findOne(id);
		model.addAttribute("client",client);
		return "editclient";
	}
	
	@RequestMapping(value="/editsave", method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("client") Client p) {
		
		Client client = clientDao.findOne(p.getId());
		
		client.setFirstName(p.getFirstName());
		client.setLastName(p.getLastName());
		client.setCountry(p.getCountry());
		client.setEmail(p.getEmail());
		client.setSection(p.getSection());
		client.setSex(p.getSex());
		
		clientDao.save(client);
		return new ModelAndView("redirect:/viewclients");
	}
	
	@RequestMapping(value="/deleteclient/{id}",  method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		Client client = clientDao.findOne(id);
		clientDao.delete(client);
		return new ModelAndView("redirect:/viewclients");
	}
	
	// Delete  all clients  function
	
	@RequestMapping(value="/deleteallclients", method=RequestMethod.GET )
	public ModelAndView deleteAllClients() {
		clientDao.deleteAll();
		return new ModelAndView("redirect:/viewclients");
	}
	
	@ModelAttribute("sections")
	public List<String> initializeSection(){
		List<String> sections = new  ArrayList<String>();
		sections.add("Under Graduate");
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Research");
		return sections;
	}
	
	@ModelAttribute("countries")
	public List<String> initilizeCountries(){
		
		List<String> countries = new ArrayList<String>();
		countries.add("UKRAINE");
		countries.add("RUSSIA");
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		
		return countries;
	}
	
	
}
