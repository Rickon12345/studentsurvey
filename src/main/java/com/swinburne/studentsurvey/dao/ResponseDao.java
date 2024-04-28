package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.Participant;
import com.swinburne.studentsurvey.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResponseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseDao() {
    }

    public Response findByStudentIdAndSemester(String surveyDate, Long id) {
        String sql = "SELECT r.instance_id,r.participant_id,r.status,r.manbox5_overall,r.bullying,r.growthMindset,r.candidate_perc_effort,r.comfortable,r.COVID, " +
                "r.pwi_wellbeing,r.isolated,r.comments,r.survey_date " +
                "FROM response r join survey s on r.participant_id = s.participant_id " +
                "join student st on s.student_id = st.id where st.id = ? and r.survey_date = ?";
        List<Response> list = this.jdbcTemplate.query(sql, new ResponseRowMapper(), id, surveyDate);
        return  list != null && list.size() > 0 ? (Response)list.get(0) : null;
    }

    public Response findClassAvg(String surveyDate, String house) {
        String sql = "SELECT 0 as instance_id, 0 as participant_id, '' as comments, '' as status, ROUND(AVG(r.manbox5_overall), 2) as manbox5_overall,ROUND(AVG(r.bullying), 2) as bullying,ROUND(AVG(r.growthMindset), 2) as growthMindset," +
                "ROUND(AVG(r.candidate_perc_effort), 2) as candidate_perc_effort,ROUND(AVG(r.comfortable), 2) as comfortable," +
                "ROUND(AVG(r.COVID), 2) as COVID,ROUND(AVG(r.pwi_wellbeing), 2) as pwi_wellbeing,ROUND(AVG(r.isolated), 2) as isolated, r.survey_date as survey_date" +
                " FROM participant p join response r on p.id = r.participant_id where p.House = ? and r.survey_date = ?";
        List<Response> list = this.jdbcTemplate.query(sql, new ResponseRowMapper(), house, surveyDate);
        return  list != null && list.size() > 0 ? (Response)list.get(0) : null;
    }

    public Response findSchoolAvg(String surveyDate) {
        String sql = "SELECT 0 as instance_id, 0 as participant_id, '' as comments, '' as status, ROUND(AVG(r.manbox5_overall), 2) as manbox5_overall,ROUND(AVG(r.bullying), 2) as bullying,ROUND(AVG(r.growthMindset), 2) as growthMindset," +
                "ROUND(AVG(r.candidate_perc_effort), 2) as candidate_perc_effort,ROUND(AVG(r.comfortable), 2) as comfortable," +
                "ROUND(AVG(r.COVID), 2) as COVID,ROUND(AVG(r.pwi_wellbeing), 2) as pwi_wellbeing,ROUND(AVG(r.isolated), 2) as isolated, r.survey_date as survey_date" +
                "  FROM response r where r.survey_date = ?";
        List<Response> list = this.jdbcTemplate.query(sql, new ResponseRowMapper(), surveyDate);
        return  list != null && list.size() > 0 ? (Response)list.get(0) : null;
    }


    class ResponseRowMapper implements RowMapper<Response> {
        ResponseRowMapper() {
        }

        public Response mapRow(ResultSet rs, int rowNum) throws SQLException {
            Response response = new Response();
            response.setInstanceId(rs.getLong("instance_id"));
            response.setParticipantId(rs.getLong("participant_id"));
            response.setStatus(rs.getString("status"));
            response.setManbox5Overall(rs.getString("manbox5_overall"));
            response.setBullying(rs.getString("bullying"));
            response.setGrowthMindset(rs.getString("growthMindset"));
            response.setCandidatePercEffort(rs.getString("candidate_perc_effort"));
            response.setComfortable(rs.getString("comfortable"));
            response.setCOVID(rs.getString("COVID"));
            response.setPwiWellbeing(rs.getString("pwi_wellbeing"));
            response.setComments(rs.getString("comments"));
            response.setIsolated(rs.getString("isolated"));
            response.setSurveyDate(rs.getString("survey_date"));

            return response;
        }
    }
}
