package com.aleksandarilic.springboot.webapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(
            ++todosCount,
            "bobrock8",
            "Learn AWS",
            LocalDate.now().plusYears(4),
            false
        ));
        todos.add(new Todo(
                ++todosCount,
                "bobrock8",
                "Learn React",
                LocalDate.now().plusYears(1),
                false
        ));
        todos.add(new Todo(
                ++todosCount,
                "bobrock8",
                "Learn Spring Boot",
                LocalDate.now().plusYears(3),
                false
        ));
        todos.add(new Todo(
                ++todosCount,
                "bobrock8",
                "Learn Typescript",
                LocalDate.now().plusYears(2),
                false
        ));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate
                = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
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

    public void deleteById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate
                = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        // first way
        deleteById(todo.getId());
        todos.add(todo);
    }
}
