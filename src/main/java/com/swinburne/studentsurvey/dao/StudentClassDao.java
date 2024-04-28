package com.swinburne.studentsurvey.dao;


import com.swinburne.studentsurvey.domain.Course;
import com.swinburne.studentsurvey.domain.Student;
import com.swinburne.studentsurvey.domain.StudentClass;
import com.swinburne.studentsurvey.domain.TeacherCourse;
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
public class StudentClassDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<StudentClass> findAll() {
		String sql = "select sc.id as id, sc.student_id as studentId, s.first_name as firstName, s.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, tc.id as classId, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime " +
				"from teacher_course tc left join student_class sc on tc.id = sc.class_id " +
				"left join student s on sc.student_id = s.id " +
				"left join course c on tc.course_id = c.id " +
				"where order by sc.created_time";
		List<StudentClass> tcList = jdbcTemplate.query(sql, new StudentClassRowMapper());
		return tcList;
	}

	public List<StudentClass> findStudentClassByStudentId(Long studentId) {
		String sql = "select sc.id as id, sc.student_id as studentId, s.first_name as firstName, s.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, tc.id as classId, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime " +
				"from teacher_course tc left join student_class sc on tc.id = sc.class_id " +
				"left join student s on sc.student_id = s.id " +
				"left join course c on tc.course_id = c.id " +
				"where sc.student_id = ? order by sc.created_time";
		List<StudentClass> tcList = jdbcTemplate.query(sql, new StudentClassRowMapper(), studentId);
		return tcList;
	}

	public List<StudentClass> findStudentClassByStudentIdAndCourseId(Long studentId, Long courseId) {
		String sql = "select sc.id as id, sc.student_id as studentId, s.first_name as firstName, s.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, tc.id as classId, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime  " +
				"from student_course tc " +
				"left join student t on tc.student_id = t.id " +
				"left join course c on tc.course_id = c.id " +
				"where tc.student_id = ? and tc.course_id = ? order by created_time";
		List<StudentClass> tcList = jdbcTemplate.query(sql, new StudentClassRowMapper(), studentId, courseId);
		return tcList;
	}

	public List<StudentClass> findStudentClassByStudentIdAndClassId(Long studentId, Long classId) {
		String sql = "select sc.id as id, sc.student_id as studentId, s.first_name as firstName, s.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, tc.id as classId, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime " +
		"from teacher_course tc left join student_class sc on tc.id = sc.class_id " +
				"left join student s on sc.student_id = s.id " +
				"left join course c on tc.course_id = c.id " +
				"where sc.student_id = ? and sc.class_id=? order by sc.created_time";
		List<StudentClass> scList = jdbcTemplate.query(sql, new StudentClassRowMapper(), studentId, classId);
		return scList;
	}

	public List<StudentClass> findStudentClassByCourseId(Long courseId) {
		String sql = "select sc.id as id, sc.student_id as studentId, s.first_name as firstName, s.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, tc.id as classId, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime  " +
				"from student_course tc " +
				"left join student t on tc.student_id = t.id " +
				"left join course c on tc.course_id = c.id " +
				"where tc.course_id = ? order by created_time";
		List<StudentClass> tcList = jdbcTemplate.query(sql, new StudentClassRowMapper(), courseId);
		return tcList;
	}

	public Integer save(StudentClass sc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (sc == null)
			return 0;
		String sql;
		if (sc.getId() != null) {
			sql = "update student_class SET  student_id=?, class_id=?,"
					+ "status=?, created_time=? " + "WHERE id = ?";
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, sc.getStudentId());
					ps.setLong(2, sc.getClassId());
					ps.setString(3, sc.getStatus());
					ps.setString(4, formatter.format(new Date().getTime()));
					ps.setLong(5, sc.getId());
				}
			});
		} else {
			sql = "insert into student_class(student_id , class_id , status, created_time) "
					+ "values(?,?,?,?)";
			return jdbcTemplate.update(sql, sc.getStudentId(), sc.getClassId(), sc.getStatus(),
					formatter.format(new Date().getTime()));
		}
	}
	public Integer deleteStudentClassByStudentIdAndClassId(Integer studentId, Integer classId) {
		String sql = "delete from student_class where student_id = ? and class_id=?";
		return jdbcTemplate.update(sql, studentId, classId);
	};
	class StudentClassRowMapper implements RowMapper<StudentClass> {
		@Override
		public StudentClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentClass sc = new StudentClass();
			sc.setId(rs.getLong("id"));
			sc.setStatus("status");
			sc.setStudentId(rs.getLong("studentId"));
			sc.setClassId(rs.getLong("classId"));
			Course course = new Course();
			course.setId(rs.getLong("courseId"));
			course.setCourseName(rs.getString("courseName"));
			sc.setCourse(course);
			Student student = new Student();
			student.setId(rs.getLong("studentId"));
			student.setFirstName(rs.getString("firstName"));
			student.setLastName(rs.getString("lastName"));
			sc.setStudent(student);
			TeacherCourse tc = new TeacherCourse();
			tc.setTeacherId(rs.getLong("teacherId"));
			tc.setStartTime(rs.getString("startTime"));
			tc.setEndTime(rs.getString("endTime"));
			if(rs.getTimestamp("startTime").before(new Date()) && rs.getTimestamp("endTime").after(new Date())){
				tc.setTimeUP("0");
			}else{
				tc.setTimeUP("1");
			}
			sc.setTeacherCourse(tc);

			return sc;
		}
	}
}
