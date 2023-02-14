package com.aleksandarilic.springboot.webapp.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "list-todos")
    public String getTodoPage(ModelMap modelMap) {
        var todos = todoService.findByUsername("Aca");
        modelMap.put("todos", todos);
        logger.info(String.valueOf(todos));
        return "list-todos";
    }
}
