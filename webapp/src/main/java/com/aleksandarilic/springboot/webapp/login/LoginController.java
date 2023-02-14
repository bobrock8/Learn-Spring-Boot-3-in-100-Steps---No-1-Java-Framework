package com.aleksandarilic.springboot.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //http://localhost:8099/login?name=aca => login.jsp
//    @RequestMapping("login")
//    public String getLoginPage(@RequestParam String name, ModelMap model) {
//        logger.info("Request param is {}", name);
//        model.put("nameA", name);
//        return "login";
//    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String getWelcomePage(
            @RequestParam String name,
            @RequestParam String password,
            ModelMap model
    ) {
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }
}
