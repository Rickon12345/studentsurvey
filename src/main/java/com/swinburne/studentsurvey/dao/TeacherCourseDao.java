package com.swinburne.studentsurvey.dao;


import com.swinburne.studentsurvey.domain.Course;
import com.swinburne.studentsurvey.domain.Teacher;
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
public class TeacherCourseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<TeacherCourse> findAll() {
		String sql = "select tc.id as id, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, c.description from teacher_course tc left join teacher t on tc.teacher_id = t.id left join course c on tc.course_id = c.id order by tc.created_time";
		List<TeacherCourse> tcList = jdbcTemplate.query(sql, new TeacherCourseRowMapper());
		return tcList;
	}

	public List<TeacherCourse> findTeacherCourseByTeacherId(Long teacherId) {
		String sql = "select tc.id as id, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, c.description from teacher_course tc left join teacher t on tc.teacher_id = t.id left join course c on tc.course_id = c.id where tc.teacher_id = ? order by tc.created_time";
		List<TeacherCourse> tcList = jdbcTemplate.query(sql, new TeacherCourseRowMapper(), teacherId);
		return tcList;
	}

	public List<TeacherCourse> findTeacherCourseByCourseId(Long courseId) {
		String sql = "select tc.id as id, tc.teacher_id as teacherId, tc.start_time as startTime, tc.end_time as endTime, t.first_name as firstName, t.last_name as lastName, tc.course_id as courseId, c.course_name as courseName, c.description  from teacher_course tc left join teacher t on tc.teacher_id = t.id left join course c on tc.course_id = c.id where tc.course_id = ? order by tc.created_time";
		List<TeacherCourse> tcList = jdbcTemplate.query(sql, new TeacherCourseRowMapper(), courseId);
		return tcList;
	}


	public Integer save(TeacherCourse tc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (tc == null)
			return 0;
		String sql;
		if (tc.getId() != null) {
			sql = "update teacher_course SET  teacher_id=?, course_id=?,"
					+ "start_time=?, end_time=?, created_time=? " + "WHERE id  = ?";
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, tc.getTeacherId());
					ps.setLong(2, tc.getCourseId());
					ps.setString(3, tc.getStartTime());
					ps.setString(4, tc.getEndTime());
					ps.setString(5, formatter.format(new Date().getTime()));
					ps.setLong(6, tc.getId());
				}
			});
		} else {
			sql = "insert into teacher_course(teacher_id , course_id , start_time, end_time, created_time) "
					+ "values(?,?,?,?,?)";
			return jdbcTemplate.update(sql, tc.getTeacherId(), tc.getCourseId(), tc.getStartTime(),tc.getEndTime(),
					formatter.format(new Date().getTime()));
		}
	}

	public Integer deleteTeacherCourseByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
		String sql = "delete from teacher_course where teacher_id = ? and course_id=?";
		return jdbcTemplate.update(sql, teacherId, courseId);
	};
	class TeacherCourseRowMapper implements RowMapper<TeacherCourse> {
		@Override
		public TeacherCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
			TeacherCourse tc = new TeacherCourse();
			tc.setId(rs.getLong("id"));
			tc.setTeacherId(rs.getLong("teacherId"));
			tc.setCourseId(rs.getLong("courseId"));
			tc.setStartTime(rs.getString("startTime"));
			tc.setEndTime(rs.getString("endTime"));
			if(rs.getTimestamp("startTime").before(new Date()) && rs.getTimestamp("endTime").after(new Date())){
				tc.setTimeUP("0");
			}else{
				tc.setTimeUP("1");
			}
			Course course = new Course();
			course.setId(rs.getLong("courseId"));
			course.setCourseName(rs.getString("courseName"));
			course.setDescription(rs.getString("description"));
			tc.setCourse(course);
			Teacher teacher = new Teacher();
			teacher.setId(rs.getLong("teacherId"));
			teacher.setFirstName(rs.getString("firstName"));
			teacher.setLastName(rs.getString("lastName"));
			tc.setTeacher(teacher);

			return tc;
		}
	}
}
