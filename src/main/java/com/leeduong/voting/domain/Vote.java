package com.leeduong.voting.domain;

/**
 * A class that represents vote information.
 * 
 * @author Lee Duong
 */
public class Vote {
	private long voterId;
	private long candidateId;

	/**
	 * @return the voter id.
	 */
	public long getVoterId() {
		return voterId;
	}
	
	/**
	 * Sets the voter id.
	 * 
	 * @param voterId
	 *            the voter id.
	 */
	public void setVoterId(long voterId) {
		this.voterId = voterId;
	}

	/**
	 * @return the candidate id.
	 */
	public long getCandidateId() {
		return candidateId;
	}

	/**
	 * Sets the candidate id.
	 * 
	 * @param voterId
	 *            the candidate id.
	 */
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vote other = (Vote) obj;
		if (voterId != other.getVoterId()) {
			return false;
		}
		if (candidateId != other.getCandidateId()) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + voterId);
		result = (int) (prime * result + candidateId);
		return result;
	}
}
