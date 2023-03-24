package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	@Autowired
	private AuthenticationService auSvc;

	@GetMapping(path = {"/", "/index.html" })
	public String getIndex(Model model, HttpSession sess) {

		User u = (User) sess.getAttribute("user");
		if (null == u) {
			u = new User();
			sess.setAttribute("user", u);
		}
		;
		model.addAttribute("user", u);
		return "view0";
	}

	@PostMapping(path = "/login")
	public String postlogin(Model model, HttpSession sess,
			@ModelAttribute @Valid User u, BindingResult bindings) throws Exception {

		u = (User) sess.getAttribute("user");
		if (null == u) {
			u = new User();
			sess.setAttribute("user", u);
		}

		if (bindings.hasErrors())
			return "view0";

		model.addAttribute("user", u);

		String username = u.getUserName();
		String password = u.getPassword();
		if (auSvc.disableUser(username)) {
			return "view2";
		}

		if (auSvc.authenticate(username, password).contains("Authenthicated")) {
			u.setAuth(true);
			model.addAttribute("user", u);
			return "view1";
		} else {
			FieldError error = new FieldError("user", "user",
					"invalid details, try again");
			bindings.addError(error);
			u.setCaptcha(true);
			return "view1";
		}

	}

	@GetMapping(path = { "/logout" })
	public String logout(Model model, HttpSession sess) {

		sess.invalidate();

		User u = (User) sess.getAttribute("user");
		if (null == u) {
			u = new User();
			sess.setAttribute("user", u);
		};
		
		model.addAttribute("user", u);
		return "view0";
	}
}
