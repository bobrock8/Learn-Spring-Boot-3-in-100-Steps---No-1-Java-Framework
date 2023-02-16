package com.aleksandarilic.learnjpaandhibernate.course.jdbc;

import com.aleksandarilic.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JdbcCommandRunner implements CommandLineRunner {

    @Autowired
    private CreateJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(
                1, "Learn AWS Cloud", "Aca"
        ));

        repository.insert(new Course(
                2, "Learn Spring Boot", "Aca"
        ));

        repository.insert(new Course(
                3, "Learn Java", "Aca"
        ));

        repository.deleteById(2);

        System.out.println(repository.findById(1));
        System.out.println(repository.findById(3));
        System.out.println(repository.findAll());
    }
}
