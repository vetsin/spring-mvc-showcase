package org.springframework.samples.mvc.form.security;

import javax.validation.Valid;

import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/form/assignment")
@SessionAttributes("assignmentBean")
public class AssignmentFormController {

	// Invoked on every request

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	
	@InitBinder("secureBean")
    protected void initBinderSecure(WebDataBinder binder) {
		binder.setAllowedFields("name");
    }
	
	@InitBinder("insecureBean")
    protected void initBinderInsecure(WebDataBinder binder) {
    }

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)

	@ModelAttribute("secureBean")
	public AssignmentBean createSecureBean() {
		AssignmentBean ab = new AssignmentBean();
		ab.setPrivateBean(new RestrictedBean());
		return ab;
	}
	
	@ModelAttribute(value="insecureBean")
	public AssignmentBean createInsecureBean() {
		AssignmentBean ab = new AssignmentBean();
		ab.setPrivateBean(new RestrictedBean());
		return ab;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String assignmentForm() {
		return "massAssignment";
	}

	@RequestMapping(value="/secure", method=RequestMethod.POST)
	public String processSecureSubmit(@Valid @ModelAttribute("secureBean") AssignmentBean secureBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return null;
		}
		
		
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Secure Form submitted successfully.  Bound " + secureBean;
		
		model.addAttribute("bean", secureBean);
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return "massAssignment";
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/form/assignment";			
		}
	}
	
	@RequestMapping(value="/insecure", method=RequestMethod.POST)
	public String processInsecureSubmit(@Valid @ModelAttribute("insecureBean") AssignmentBean insecureBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return null;
		}
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Insecure Form submitted successfully.  Bound " + insecureBean;
		
		model.addAttribute("bean", insecureBean);
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return "massAssignment";
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/form/assignment";			
		}
	}
}
