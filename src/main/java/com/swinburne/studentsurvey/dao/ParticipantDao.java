package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Participant;
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

    public List<Participant> findByStudentId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p join survey s on p.id = s.participant_id " +
                " join student st on s.student_id = st.id where st.id = ?";
        return this.jdbcTemplate.query(sql, new ParticipantDao.ParticipantRowMapper(), new Object[]{id});
    }

    public List<Participant> findFriendByParticipantId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM friends f join participant p on p.id=f.target where participant_id=?";
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
