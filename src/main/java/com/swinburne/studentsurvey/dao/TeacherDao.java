package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Teacher;
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
public class TeacherDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TeacherDao() {
    }

    public List<Teacher> findAll() {
        return this.jdbcTemplate.query("select id , first_name , last_name , email , password , gender , status , registration_date , date_of_birth from teacher", new TeacherRowMapper());
    }

    public Teacher teacherLogin(Teacher teacher) {
        List<Teacher> teacherList = this.jdbcTemplate.query("select * from teacher where email = ? and password = ?", new TeacherRowMapper(), new Object[]{teacher.getEmail(), teacher.getPassword()});
        return teacherList != null && teacherList.size() > 0 ? (Teacher)teacherList.get(0) : null;
    }

    public Teacher findById(Long id) {
        String sql = "select * from teacher where id = ?";
        Teacher teacher = (Teacher)this.jdbcTemplate.queryForObject(sql, new TeacherRowMapper(), new Object[]{id});
        return teacher;
    }

    public Integer save(final Teacher teacher) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (teacher == null) {
            return 0;
        } else {
            String sql;
            if (teacher.getId() != null) {
                sql = "update teacher SET first_name  = ?,last_name  = ?, email  =?,password  = ?,gender  = ?, status  = ?, registration_date  = ?,date_of_birth  = ? WHERE  id  = ?";
                return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, teacher.getFirstName());
                        ps.setString(2, teacher.getLastName());
                        ps.setString(3, teacher.getEmail());
                        ps.setString(4, teacher.getPassword());
                        ps.setString(5, teacher.getGender());
                        ps.setString(6, teacher.getStatus());
                        ps.setString(7, formatter.format((new Date()).getTime()));
                        ps.setString(8, teacher.getDateOfBirth());
                        ps.setLong(9, teacher.getId());
                    }
                });
            } else {
                sql = "insert into teacher(first_name , last_name , email , password , gender , status , registration_date , date_of_birth ) values(?,?,?,?,?,?,?,?)";
                return this.jdbcTemplate.update(sql, new Object[]{teacher.getFirstName(), teacher.getLastName(), teacher.getEmail(), teacher.getPassword(), teacher.getGender(), teacher.getStatus(), formatter.format((new Date()).getTime()), teacher.getDateOfBirth()});
            }
        }
    }

    public Integer deleteById(Long id) {
        String sql = "delete from teacher where id = ?";
        return this.jdbcTemplate.update(sql, new Object[]{id});
    }

    class TeacherRowMapper implements RowMapper<Teacher> {
        TeacherRowMapper() {
        }

        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getLong("id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setEmail(rs.getString("email"));
            teacher.setPassword(rs.getString("password"));
            teacher.setGender(rs.getString("gender"));
            teacher.setStatus(rs.getString("status"));
            teacher.setRegistrationDate(rs.getString("registration_date"));
            teacher.setDateOfBirth(rs.getString("date_of_birth"));

            return teacher;
        }
    }
}
