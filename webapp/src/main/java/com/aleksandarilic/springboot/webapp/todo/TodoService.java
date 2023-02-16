package com.aleksandarilic.springboot.webapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(
            ++todosCount,
            "in28minutes",
            "Learn AWS",
            LocalDate.now().plusYears(4),
            false
        ));
        todos.add(new Todo(
                ++todosCount,
                "in28minutes",
                "Learn React",
                LocalDate.now().plusYears(1),
                false
        ));
        todos.add(new Todo(
                ++todosCount,
                "in28minutes",
                "Learn Spring Boot",
                LocalDate.now().plusYears(3),
                false
        ));
        todos.add(new Todo(
                ++todosCount,
                "in28minutes",
                "Learn Typescript",
                LocalDate.now().plusYears(2),
                false
        ));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetData, boolean done){
        todos.add(new Todo(
                ++todosCount,
                username,
                description,
                targetData,
                done
        ));
    }
}
