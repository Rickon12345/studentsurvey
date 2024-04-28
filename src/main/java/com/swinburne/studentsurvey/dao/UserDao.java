package com.swinburne.studentsurvey.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.swinburne.studentsurvey.domain.User;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDao() {
    }

    public List<User> findAll() {
        return this.jdbcTemplate.query("select * from user", new UserRowMapper());
    }

    public User userLogin(User user) {
        List<User> userList = this.jdbcTemplate.query("select * from user where user_name = ? and user_password = ?", new UserRowMapper(), new Object[]{user.getUserName(), user.getUserPassword()});
        return userList != null && userList.size() > 0 ? (User)userList.get(0) : null;
    }

    public User findById(Long id) {
        String sql = "select * from user where id = ?";
        User user = (User)this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), new Object[]{id});
        return user;
    }

    public Integer save(final User user) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (user == null) {
            return 0;
        } else {
            final byte role;
            if (user.getRole() != null && "administrator".equals(user.getRole())) {
                role = 1;
            } else {
                role = 0;
            }

            String sql;
            if (user.getId() != null) {
                sql = "update user SET user_name  = ?,user_password  = ?, phone_number  =?,gender  = ?,email  = ?, status  = ?, created_time  = ?,role  = ?, active  =? WHERE  id  = ?";
                return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, user.getUserName());
                        ps.setString(2, user.getUserPassword());
                        ps.setString(3, user.getPhoneNumber());
                        ps.setString(4, user.getGender());
                        ps.setString(5, user.getEmail());
                        ps.setString(6, user.getStatus());
                        ps.setString(7, formatter.format((new Date()).getTime()));
                        ps.setInt(8, role);
                        ps.setString(9, user.getActive());
                        ps.setLong(10, user.getId());
                    }
                });
            } else {
                sql = "insert into user(user_name , user_password , phone_number , gender , email , status , created_time , role , active ) values(?,?,?,?,?,?,?,?,?)";
                return this.jdbcTemplate.update(sql, new Object[]{user.getUserName(), user.getUserPassword(), user.getPhoneNumber(), user.getGender(), user.getEmail(), user.getStatus(), formatter.format((new Date()).getTime()), Integer.valueOf(role), user.getActive()});
            }
        }
    }

    public Integer deleteById(Long id) {
        String sql = "delete from user where id = ?";
        return this.jdbcTemplate.update(sql, new Object[]{id});
    }

    class UserRowMapper implements RowMapper<User> {
        UserRowMapper() {
        }

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserPassword(rs.getString("user_password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setStatus(rs.getString("status"));
            user.setCreatedTime(rs.getString("created_time"));
            String role = "user";
            if (rs.getInt("role") == 1) {
                role = "administrator";
            }

            user.setRole(role);
            return user;
        }
    }
}
