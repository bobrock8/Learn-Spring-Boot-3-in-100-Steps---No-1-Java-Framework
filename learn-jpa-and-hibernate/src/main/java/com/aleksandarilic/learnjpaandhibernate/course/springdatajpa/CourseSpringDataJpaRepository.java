package com.aleksandarilic.learnjpaandhibernate.course.springdatajpa;

import com.aleksandarilic.learnjpaandhibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

}
