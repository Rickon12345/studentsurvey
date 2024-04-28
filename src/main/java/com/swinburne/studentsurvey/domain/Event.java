package com.swinburne.studentsurvey.domain;

import java.util.Objects;

public class Event {
    private String result;

    public Event() {
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
	public int hashCode() {
		return Objects.hash(result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(result, other.result);
	}
}
