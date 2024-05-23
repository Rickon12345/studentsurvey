package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Participant;
import com.swinburne.studentsurvey.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipantDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ParticipantDao() {
    }

    public Participant findByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p where p.id = ?";
        List<Participant> list = this.jdbcTemplate.query(sql, new ParticipantRowMapper(), id);
        return  list != null && list.size() > 0 ? (Participant) list.get(0) : null;
    }

    public List<Participant> findByStudentId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p join survey s on p.id = s.participant_id " +
                " join student st on s.student_id = st.id where st.id = ?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findAllBySemester(String surveyDate) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p  where p.survey_date = ?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{surveyDate});
    }

    public Participant findByStudentIdAndSemester(String surveyDate, Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p join survey s on p.id = s.participant_id " +
                " join student st on s.student_id = st.id where st.id = ? and p.survey_date = ?";
        List<Participant> list = this.jdbcTemplate.query(sql, new ParticipantRowMapper(), id, surveyDate);
        return  list != null && list.size() > 0 ? (Participant)list.get(0) : null;
    }

    public List<Participant> findFromFriendByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM friends f join participant p on p.id=f.target where f.participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToFriendByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM friends f join participant p on p.id=f.participant_id where f.target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFromInfluentialByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM influential f join participant p on p.id=f.target where participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToInfluentialByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM influential f join participant p on p.id=f.participant_id where target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFromDisrespectByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM disrespect f join participant p on p.id=f.target where participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToDisrespectByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM disrespect f join participant p on p.id=f.participant_id where target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFromFeedbackByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM feedback f join participant p on p.id=f.target where participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToFeedbackByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM feedback f join participant p on p.id=f.participant_id where target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFromMoreTimeByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM moretime f join participant p on p.id=f.target where participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToMoreTimeByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM moretime f join participant p on p.id=f.participant_id where target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFromAdviceByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM advice f join participant p on p.id=f.target where participant_id=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findToAdviceByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM advice f join participant p on p.id=f.participant_id where target=?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    class ParticipantRowMapper implements RowMapper<Participant> {
        ParticipantRowMapper() {
        }

        public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Participant participant = new Participant();
            participant.setId(rs.getLong("id"));
            participant.setFirstName(rs.getString("first_name"));
            participant.setLastName(rs.getString("last_name"));
            participant.setEmail(rs.getString("email"));
            participant.setContactNumber(rs.getString("contact_number"));
            participant.setPercEffort(rs.getString("perc_effort"));
            participant.setAttendance(rs.getString("attendance"));
            participant.setPercAcademic(rs.getString("perc_academic"));
            participant.setCompleteYears(rs.getString("completeYears"));
            participant.setHouse(rs.getString("house"));
            participant.setSurveyDate(rs.getString("survey_date"));

            return participant;
        }
    }
}
