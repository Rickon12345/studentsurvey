package com.swinburne.studentsurvey.domain;

public class NodeAnalytics {
	private Long nodeId;
	private Long nodeLabel;
	private Long nodeIn;
	private Long nodeOut;
	private String inDegree;
	private String outDegree;
	private String eigenvector;
	private String betweenness;
	private String closeness;
	private String Clustering;
	private String nodeReciprocity;
	private String Community;
	private String surveyDate;
	private Participant participant;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(Long nodeLabel) {
		this.nodeLabel = nodeLabel;
	}

	public Long getNodeIn() {
		return nodeIn;
	}

	public void setNodeIn(Long nodeIn) {
		this.nodeIn = nodeIn;
	}

	public Long getNodeOut() {
		return nodeOut;
	}

	public void setNodeOut(Long nodeOut) {
		this.nodeOut = nodeOut;
	}

	public String getInDegree() {
		return inDegree;
	}

	public void setInDegree(String inDegree) {
		this.inDegree = inDegree;
	}

	public String getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(String outDegree) {
		this.outDegree = outDegree;
	}

	public String getEigenvector() {
		return eigenvector;
	}

	public void setEigenvector(String eigenvector) {
		this.eigenvector = eigenvector;
	}

	public String getBetweenness() {
		return betweenness;
	}

	public void setBetweenness(String betweenness) {
		this.betweenness = betweenness;
	}

	public String getCloseness() {
		return closeness;
	}

	public void setCloseness(String closeness) {
		this.closeness = closeness;
	}

	public String getClustering() {
		return Clustering;
	}

	public void setClustering(String clustering) {
		Clustering = clustering;
	}

	public String getNodeReciprocity() {
		return nodeReciprocity;
	}

	public void setNodeReciprocity(String nodeReciprocity) {
		this.nodeReciprocity = nodeReciprocity;
	}

	public String getCommunity() {
		return Community;
	}

	public void setCommunity(String community) {
		Community = community;
	}

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}