package co.com.ceiba.parqueadero.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.com.ceiba.parqueadero.model.ContactModel;
import co.com.ceiba.parqueadero.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showContacts";
	}
	
	@GetMapping("/contactForm")
	private	String redirectContactForm(@RequestParam(name="id", required=false) int id, 
			Model model) {
		ContactModel contact = new ContactModel();
		if(id != 0) {
			contact = contactService.findContactByIdModel(id);
		}
		model.addAttribute("contactModel", contact);
		return "contactForm";
	}
	
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model) {
		LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
		if(null != contactService.addContact(contactModel)) {
			model.addAttribute("result", 1);	
		}else {
			model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showContacts";
	}
	
	@GetMapping("/showContacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView("contacts");
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;
	}
	
	@GetMapping("/removeContact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return showContacts();
	}
}
