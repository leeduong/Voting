package com.leeduong.voting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * A class that represents a candidate.
 * 
 * @author Lee Duong
 */
@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private long votes;

	public Candidate() {
	}
	
	public Candidate(String name, long votes) {
		this.name = name;
		this.votes = votes;
	}

	/**
	 * @return the candidate id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the candidate name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the number of votes for this candidate.
	 */
	public long getVotes() {
		return votes;
	}
	
	/**
	 * Sets the number of votes for this candidate.
	 * 
	 * @param votes
	 *            the number of votes for this candidate.
	 */
	public void setVotes(long votes) {
		this.votes = votes;
	}	
}
