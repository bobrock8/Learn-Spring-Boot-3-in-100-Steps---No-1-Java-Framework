package com.aleksandarilic.learnjpaandhibernate.course;

import com.aleksandarilic.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.aleksandarilic.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandRunner implements CommandLineRunner {

    //@Autowired
    //private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;


    @Override
    public void run(String... args) throws Exception {
//        repository.insert(new Course(
//                1, "Learn AWS Cloud", "Aca"
//        ));
//
//        repository.insert(new Course(
//                2, "Learn Spring Boot", "Aca"
//        ));
//
//        repository.insert(new Course(
//                3, "Learn Java", "Aca"
//        ));
//
//        repository.deleteById(2);
//
//        System.out.println(repository.findById(1));
//        System.out.println(repository.findById(3));

        repository.save(new Course(
                1, "Learn AWS Cloud", "Aca"
        ));

        repository.save(new Course(
                2, "Learn Spring Boot", "Aca"
        ));

        repository.save(new Course(
                3, "Learn Java", "Aca"
        ));

        repository.deleteById(1L);

        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(3L));

        System.out.println(repository.findAll());
    }
}
