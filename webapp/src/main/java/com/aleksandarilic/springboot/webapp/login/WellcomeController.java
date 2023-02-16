package com.aleksandarilic.springboot.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WellcomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // private AuthenticationService authenticationService;

//    public LoginController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
    //http://localhost:8099/login?name=aca => login.jsp
//    @RequestMapping("login")
//    public String getLoginPage(@RequestParam String name, ModelMap model) {
//        logger.info("Request param is {}", name);
//        model.put("nameA", name);
//        return "login";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage(ModelMap model) {
        model.put("name", "HARDCODED NAME");
        return "welcome";
    }
    // REMOVE LOGIN
    // REMOVE login.jsp
    // REMOVE AUTh service

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String getWelcomePage(
//            @RequestParam String name,
//            @RequestParam String password,
//            ModelMap model
//    ) {
//
//        if (this.authenticationService.authenticate(name, password)) {
//            model.put("name", name);
//            model.put("password", password);
//            return "welcome";
//        }
//
//        model.put("errorMessage", "Invalid Credentials");
//        return "login";
//    }
}
