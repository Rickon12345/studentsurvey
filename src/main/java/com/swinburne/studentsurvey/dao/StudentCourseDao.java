package com.swinburne.studentsurvey.dao;


import com.swinburne.studentsurvey.domain.Course;
import com.swinburne.studentsurvey.domain.Student;
import com.swinburne.studentsurvey.domain.StudentCourse;
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
public class StudentCourseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<StudentCourse> findAll() {
		String sql = "select tc.id as id, tc.student_id as studentId, tc.status as status, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName from student_course tc left join student t on tc.student_id = t.id left join course c on tc.course_id = c.id order by created_time";
		List<StudentCourse> tcList = jdbcTemplate.query(sql, new StudentCourseRowMapper());
		return tcList;
	}

	public List<StudentCourse> findStudentCourseByStudentId(Long studentId) {
		String sql = "select tc.id as id, tc.student_id as studentId, tc.status as status, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName,c.description  from student_course tc left join student t on tc.student_id = t.id left join course c on tc.course_id = c.id where tc.student_id = ? order by tc.created_time";
		List<StudentCourse> tcList = jdbcTemplate.query(sql, new StudentCourseRowMapper(), studentId);
		return tcList;
	}

	public List<StudentCourse> findStudentCourseByCourseId(Long courseId) {
		String sql = "select tc.id as id, tc.student_id as studentId, tc.status as status, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName  from student_course tc left join student t on tc.student_id = t.id left join course c on tc.course_id = c.id where tc.course_id = ? order by created_time";
		List<StudentCourse> tcList = jdbcTemplate.query(sql, new StudentCourseRowMapper(), courseId);
		return tcList;
	}


	public Integer save(StudentCourse tc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (tc == null)
			return 0;
		String sql;
		if (tc.getId() != null) {
			sql = "update student_course SET  student_id=?, course_id=?,"
					+ "status=?, created_time=? " + "WHERE id = ?";
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, tc.getStudentId());
					ps.setLong(2, tc.getCourseId());
					ps.setString(3, tc.getStatus());
					ps.setString(5, formatter.format(new Date().getTime()));
					ps.setLong(6, tc.getId());
				}
			});
		} else {
			sql = "insert into student_course(student_id , course_id , status, created_time) "
					+ "values(?,?,?,?)";
			return jdbcTemplate.update(sql, tc.getStudentId(), tc.getCourseId(), tc.getStatus(),
					formatter.format(new Date().getTime()));
		}
	}
	public Integer deleteStudentCourseByStudentIdAndCourseId(Integer studentId, Integer courseId) {
		String sql = "delete from student_course where student_id = ? and course_id=?";
		return jdbcTemplate.update(sql, studentId, courseId);
	};
	class StudentCourseRowMapper implements RowMapper<StudentCourse> {
		@Override
		public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentCourse tc = new StudentCourse();
			tc.setId(rs.getLong("id"));
			tc.setStatus(rs.getString("status"));
			tc.setStudentId(rs.getLong("studentId"));
			tc.setCourseId(rs.getLong("courseId"));
			Course course = new Course();
			course.setId(rs.getLong("courseId"));
			course.setCourseName(rs.getString("courseName"));
			course.setDescription(rs.getString("description"));
			tc.setCourse(course);
			Student student = new Student();
			student.setId(rs.getLong("studentId"));
			student.setFirstName(rs.getString("firstName"));
			student.setLastName(rs.getString("lastName"));
			tc.setStudent(student);

			return tc;
		}
	}
}
