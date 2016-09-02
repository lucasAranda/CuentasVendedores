package com.lucas.account.web;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.lucas.account.model.Vendedor;
import com.lucas.account.service.VendedorService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class AccountInfoController {

	@Autowired
	private VendedorService vendedorService;

	private static final Logger logger = LoggerFactory.getLogger(AccountInfoController.class);


	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Model model){
		List<String> vendedores = vendedorService.obtenerVendedores();
		model.addAttribute("lstVendedores", vendedores);

		return "info";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		//LoadDataService dataService = new LoadDataService();

		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Vendedor vendedor = vendedorService.obtenerComprobantes(user.getUsername()); //get logged in username
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("vendedor", vendedor);
		model.addAttribute("lstComprobante", mapper.writeValueAsString(vendedor.getComprobantes()));
		//model.addAttribute("employeeList", mapper.writeValueAsString(dataService.getEmployeeList()));
		return "user/home";
	}
	
}
