package com.aleksandarilic.springboot.webapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(
            1,
            "in28minutes",
            "Learn AWS",
            LocalDate.now().plusYears(4),
            false
        ));
        todos.add(new Todo(
                2,
                "in28minutes",
                "Learn React",
                LocalDate.now().plusYears(1),
                false
        ));
        todos.add(new Todo(
                3,
                "in28minutes",
                "Learn Spring Boot",
                LocalDate.now().plusYears(3),
                false
        ));
        todos.add(new Todo(
                4,
                "in28minutes",
                "Learn Typescript",
                LocalDate.now().plusYears(2),
                false
        ));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }
}
