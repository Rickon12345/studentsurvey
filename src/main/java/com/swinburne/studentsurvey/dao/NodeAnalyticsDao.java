package com.swinburne.studentsurvey.dao;

import com.swinburne.studentsurvey.domain.NodeAnalytics;
import com.swinburne.studentsurvey.domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NodeAnalyticsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public NodeAnalyticsDao() {
    }

    public List<NodeAnalytics> findNodeAnalyticsBySurveyDate(String surveyDate) {
        String sql = "SELECT n.node_Id,n.node_Label,n.node_in,n.node_out,n.in_degree_centrality," +
                "n.out_degree_centrality,n.eigenvector_centrality,n.betweenness_centrality," +
                "n.closeness_centrality,n.Clustering,n.nodeReciprocity,n.Community," +
                "n.survey_date, p.email,p.first_name, p.last_name, p.contact_number " +
                "FROM nodesAnalytics n join participant p on p.id=n.node_Label where n.survey_date=?";
        return this.jdbcTemplate.query(sql, new NodeAnalyticsDao.NodeAnalyticsRowMapper(), new Object[]{surveyDate});
    }

    public List<NodeAnalytics> findTopBySurveyDateAndType(String surveyDate, String type) {
        String sql = "SELECT n.node_Id,n.node_Label,n.node_in,n.node_out,n.in_degree_centrality,n.out_degree_centrality," +
                "n.eigenvector_centrality,n.betweenness_centrality,n.closeness_centrality,n.Clustering,n.nodeReciprocity," +
                "n.Community ,n.survey_date, p.email,p.first_name, p.last_name, p.contact_number " +
                "FROM nodesAnalytics n join participant p on p.id=n.node_Label where n.survey_date=? order by "+ type + " desc limit 10;";
        return this.jdbcTemplate.query(sql, new NodeAnalyticsDao.NodeAnalyticsRowMapper(), new Object[]{surveyDate});
    }



    class NodeAnalyticsRowMapper implements RowMapper<NodeAnalytics> {
        NodeAnalyticsRowMapper() {
        }

        public NodeAnalytics mapRow(ResultSet rs, int rowNum) throws SQLException {
            NodeAnalytics nodeAnalytics = new NodeAnalytics();
            nodeAnalytics.setNodeId(rs.getLong("node_Id"));
            nodeAnalytics.setNodeLabel(rs.getLong("node_Label"));
            nodeAnalytics.setNodeIn(rs.getLong("node_in"));
            nodeAnalytics.setNodeOut(rs.getLong("node_out"));
            nodeAnalytics.setInDegree(rs.getString("in_degree_centrality"));
            nodeAnalytics.setOutDegree(rs.getString("out_degree_centrality"));
            nodeAnalytics.setEigenvector(rs.getString("eigenvector_centrality"));
            nodeAnalytics.setBetweenness(rs.getString("betweenness_centrality"));
            nodeAnalytics.setCloseness(rs.getString("closeness_centrality"));
            nodeAnalytics.setClustering(rs.getString("Clustering"));
            nodeAnalytics.setNodeReciprocity(rs.getString("nodeReciprocity"));
            nodeAnalytics.setCommunity(rs.getString("Community"));
            nodeAnalytics.setSurveyDate(rs.getString("survey_date"));

            Participant p = new Participant();
            p.setEmail(rs.getString("email"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setContactNumber(rs.getString("contact_number"));
            nodeAnalytics.setParticipant(p);

            return nodeAnalytics;
        }
    }
}
