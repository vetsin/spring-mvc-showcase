package org.springframework.samples.mvc.csrf;

import org.springframework.samples.mvc.data.JavaBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/csrf")
public class CsrfController 
{
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody String noCsrf() {
		return "CSRF Get Request";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody String withCsrf() {
		return "CSRF Save Request";
	}
}
