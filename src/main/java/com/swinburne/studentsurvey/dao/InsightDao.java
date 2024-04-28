package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Classroom;
import com.swinburne.studentsurvey.domain.Insight;
import com.swinburne.studentsurvey.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InsightDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InsightDao() {
    }

    public List<Insight> findInsightBySemester(String surveyDate) {
        String sql = "select p.house, ROUND(avg(Perc_Academic), 2) as Perc_Academic, ROUND(avg(p.attendance), 2) as attendance, ROUND(avg(p.perc_Academic), 2) as perc_Academic, ROUND(avg(p.perc_effort), 2) as perc_effort," +
                "ROUND(avg(r.growthMindset), 2) as growthMindset, ROUND(avg(r.manbox5_overall), 2) as manbox5_overall, ROUND(avg(r.masculinity_contrained), 2) as masculinity_contrained " +
                " from participant p join response r on p.id=r.participant_id and p.survey_date = ? group by p.house;";
        List<Insight> list = jdbcTemplate.query(sql, new InsightDao.InsightRowMapper(), surveyDate);
        return list;
    }

    class InsightRowMapper implements RowMapper<Insight> {
        InsightRowMapper() {
        }

        public Insight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Insight insight = new Insight();
            insight.setHouse(rs.getString("house"));
            insight.setPerc_Academic(rs.getString("Perc_Academic"));
            insight.setAttendance(rs.getString("attendance"));
            insight.setPerc_Academic(rs.getString("perc_Academic"));
            insight.setPerc_effort(rs.getString("perc_effort"));
            insight.setGrowthMindset(rs.getString("growthMindset"));
            insight.setManbox5_overall(rs.getString("manbox5_overall"));
            insight.setMasculinity_contrained(rs.getString("masculinity_contrained"));

            return insight;
        }
    }
}
