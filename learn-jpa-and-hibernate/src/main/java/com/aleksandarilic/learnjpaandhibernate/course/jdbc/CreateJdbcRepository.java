package com.aleksandarilic.learnjpaandhibernate.course.jdbc;

import com.aleksandarilic.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CreateJdbcRepository {


    @Autowired
    private JdbcTemplate template;


    private static final String SELECT_QUERY =
            """
                   select * from course
                    """;

    private static final String SELECT_BY_ID_QUERY =
            """
                   select * from course where id=?
                    """;

    private static String INSERT_QUERY =
            """
            insert into course(id, name, author)
            values(?, ?, ?);
            """;


    private static final String DELETE_QUERY =
            """
                   delete from course where id=?
                    """;

    public void insert(Course course) {
        template.update(
                INSERT_QUERY,
                course.getId(),
                course.getName(),
                course.getAuthor()
        );
    }

    public void deleteById(long id) {
        template.update(DELETE_QUERY, id);
    }

    public Course findById(long id) {
       return template.queryForObject(
                SELECT_BY_ID_QUERY,
                new BeanPropertyRowMapper<>(Course.class),
                id
        );
    };

    public List<Course> findAll() {
        return template.query(
                SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class)
        );
    };
}
