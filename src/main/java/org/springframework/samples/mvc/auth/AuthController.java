package org.springframework.samples.mvc.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController 
{
	@RequestMapping(value="anonymous", method=RequestMethod.GET)
	public @ResponseBody String anonymous() {
		return "Request is anonymous";
	}
	
	@RequestMapping(value="useronly", method=RequestMethod.GET)
	public @ResponseBody String userOnly() {
		return "Request is for User only";
	}
}
