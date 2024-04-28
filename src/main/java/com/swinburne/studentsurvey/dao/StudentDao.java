package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StudentDao() {
    }

    public List<Student> findAll() {
        return this.jdbcTemplate.query("select id , first_name , last_name , email , password , gender , status , registration_date , date_of_birth from student", new StudentRowMapper());
    }

    public Student studentLogin(Student student) {
        List<Student> studentList = this.jdbcTemplate.query("select * from student where email = ? and password = ?", new StudentRowMapper(), new Object[]{student.getEmail(), student.getPassword()});
        return studentList != null && studentList.size() > 0 ? (Student)studentList.get(0) : null;
    }

    public Student findById(Long id) {
        String sql = "select * from student where id = ?";
        Student student = (Student)this.jdbcTemplate.queryForObject(sql, new StudentRowMapper(), new Object[]{id});
        return student;
    }

    public Integer save(final Student student) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (student == null) {
            return 0;
        } else {
            String sql;
            if (student.getId() != null) {
                sql = "update student SET first_name  = ?,last_name  = ?, email  =?,password  = ?,gender  = ?, status  = ?, registration_date  = ?,date_of_birth  = ? WHERE  id  = ?";
                return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, student.getFirstName());
                        ps.setString(2, student.getLastName());
                        ps.setString(3, student.getEmail());
                        ps.setString(4, student.getPassword());
                        ps.setString(5, student.getGender());
                        ps.setString(6, student.getStatus());
                        ps.setString(7, formatter.format((new Date()).getTime()));
                        ps.setString(8, student.getDateOfBirth());
                        ps.setLong(9, student.getId());
                    }
                });
            } else {
                sql = "insert into student(first_name , last_name , email , password , gender , status , registration_date , date_of_birth ) values(?,?,?,?,?,?,?,?)";
                return this.jdbcTemplate.update(sql, new Object[]{student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword(), student.getGender(), student.getStatus(), formatter.format((new Date()).getTime()), student.getDateOfBirth()});
            }
        }
    }

    public Integer deleteById(Long id) {
        String sql = "delete from student where id = ?";
        return this.jdbcTemplate.update(sql, new Object[]{id});
    }

    class StudentRowMapper implements RowMapper<Student> {
        StudentRowMapper() {
        }

        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setEmail(rs.getString("email"));
            student.setPassword(rs.getString("password"));
            student.setGender(rs.getString("gender"));
            student.setStatus(rs.getString("status"));
            student.setRegistrationDate(rs.getString("registration_date"));
            student.setDateOfBirth(rs.getString("date_of_birth"));

            return student;
        }
    }
}
