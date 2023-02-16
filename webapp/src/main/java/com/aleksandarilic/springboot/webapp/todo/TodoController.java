package com.aleksandarilic.springboot.webapp.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

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

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(
                0,
                username,
                "Default Description",
                LocalDate.now().plusYears(1),
                false
        );
        model.put("todo", todo);
        return "add-todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "add-todo";
        }

        String username = (String)model.get("name");
        todoService.addTodo(
                username,
                todo.getDescription(),
                LocalDate.now().plusMonths(4),
                false
        );
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }
}
