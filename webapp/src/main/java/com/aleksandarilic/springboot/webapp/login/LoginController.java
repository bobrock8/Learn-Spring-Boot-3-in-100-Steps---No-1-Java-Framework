package com.aleksandarilic.springboot.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //http://localhost:8099/login => login.jsp?name=Aca
    @RequestMapping("login")
    public String getLoginPage(@RequestParam String name, ModelMap model) {
        logger.info("Request param is {}", name);
        model.put("nameA", name);
        return "login";
    }
}
