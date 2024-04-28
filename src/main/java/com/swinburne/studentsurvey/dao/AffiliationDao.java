package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Affiliation;
import com.swinburne.studentsurvey.domain.AffiliationGroup;
import com.swinburne.studentsurvey.domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AffiliationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AffiliationDao() {
    }

    public List<Affiliation> findByStudentId(Long id) {
        String sql = "SELECT p.id,p.first_name,p.last_name,p.email,contact_number,perc_effort,attendance,Perc_Academic,CompleteYears,House,p.survey_date" +
                " FROM participant p join survey s on p.id = s.participant_id " +
                " join student st on s.student_id = st.id where st.id = ?";
        return this.jdbcTemplate.query(sql, new AffiliationDao.AffiliationRowMapper(), new Object[]{id});
    }

    public List<AffiliationGroup> groupAffiliation(String surveyDate) {
        String sql = "SELECT a.title,count(*) as count,s.survey_date FROM affiliation a join SchoolActivityNet s on a.id = s.affiliation_id where s.survey_date = ? group by a.title,s.survey_date";
        return this.jdbcTemplate.query(sql, new AffiliationDao.AffiliationGroupRowMapper(), new Object[]{surveyDate});
    }

    class AffiliationGroupRowMapper implements RowMapper<AffiliationGroup> {
        AffiliationGroupRowMapper() {
        }

        public AffiliationGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            AffiliationGroup affiliationGroup = new AffiliationGroup();
            affiliationGroup.setAffiliationTitle(rs.getString("title"));
            affiliationGroup.setCount(rs.getString("count"));
            affiliationGroup.setSurveyDate(rs.getString("survey_date"));

            return affiliationGroup;
        }
    }

    class AffiliationRowMapper implements RowMapper<Affiliation> {
        AffiliationRowMapper() {
        }

        public Affiliation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Affiliation affiliation = new Affiliation();
            affiliation.setId(rs.getLong("id"));
            affiliation.setTitle(rs.getString("title"));
            affiliation.setCategory(rs.getString("category"));
            affiliation.setDescription(rs.getString("description"));
            affiliation.setNominationwave(rs.getString("nominationwave"));
            affiliation.setAddtional1(rs.getString("addtional1"));
            affiliation.setAddtional2(rs.getString("addtional2"));

            return affiliation;
        }
    }
}
