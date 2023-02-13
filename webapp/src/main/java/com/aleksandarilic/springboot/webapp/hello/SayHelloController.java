package com.aleksandarilic.springboot.webapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        //http://localhost:8099/say-hello
        return "Hello! What are you learning today?";
    }


    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        //http://localhost:8099/say-hello-html
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<head>");
        stringBuffer.append("<title>");
        stringBuffer.append("My first HTML Page");
        stringBuffer.append("</title>");
        stringBuffer.append("</head>");
        stringBuffer.append("<body>");
        stringBuffer.append("<h1>");
        stringBuffer.append("Say Hello HTML Page");
        stringBuffer.append("</h1>");
        stringBuffer.append("</body>");
        stringBuffer.append("</html>");

        return stringBuffer.toString();
    }

    //JSP - sayHello.jsp

    //http://localhost:8099/say-hello-jsp => sayHello.jsp
    // ${project.basedir}/main/webapp/WEB-INF/jsp/
    @RequestMapping("say-hello-jsp")
    public String  sayHelloJsp() {
        return "sayHello";
    }
}
