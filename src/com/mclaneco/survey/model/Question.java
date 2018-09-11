package com.mclaneco.survey.model;

import java.util.List;

public class Question {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}

	int id;
	String description;
	int surveyid;
	int lookupid;
	String creationdate;
	int createdby;
	int lastupdatedby;
	String lastupdatedate;
	int sitenumber;
	String customeremail;
	String campaignguid;
	Type type;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getSurveyID() {
		return surveyid;
	}

	public void setSurveyID(int surveyid) {
		this.surveyid = surveyid;
	}
	
	public int getLookupID() {
		return lookupid;
	}

	public void setLookupID(int lookupid) {
		this.lookupid = lookupid;
	}
	
	public String getCreationDate() {
		return creationdate;
	}

	public void setCreationDate(String creationdate) {
		this.creationdate = creationdate;
	}
	
	public int getCreatedBy() {
		return createdby;
	}

	public void setCreatedBy(int createdby) {
		this.createdby = createdby;
	}
	
	public String getLastUpdateDate() {
		return lastupdatedate;
	}

	public void setLastUpdateDate(String lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}
	
	public int getLastUpdatedBy() {
		return lastupdatedby;
	}

	public void setLastUpdatedBy(int lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
	public int getSiteNumber() {
		return sitenumber;
	}

	public void setSiteNumber(int sitenumber) {
		this.sitenumber = sitenumber;
	}
	
	public String getCustomerEmail() {
		return customeremail;
	}

	public void setCustomerEmail(String customeremail) {
		this.customeremail = customeremail;
	}
	
	public String getCampaignGuid() {
		return campaignguid;
	}

	public void setCampaignGuid(String campaignguid) {
		this.campaignguid = campaignguid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Choice> getQuestion_choices() {
		return question_choices;
	}

	public void setQuestion_choices(List<Choice> question_choices) {
		this.question_choices = question_choices;
	}

	List<Choice> question_choices;
	
	public static class Type {
		public int getId() {
			return id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Type other = (Type) obj;
			if (id != other.id)
				return false;
			return true;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		int id;
		String description;
		
	}
}
