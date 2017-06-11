package com.leeduong.voting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * A class that represents a voter.
 * 
 * @author Lee Duong
 */
@Entity
public class Voter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private int voteCount;

	public Voter() {
	}
	
	public Voter(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the voter id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the number of votes made.
	 */
	public long getVoteCount() {
		return voteCount;
	}
	
	/**
	 * Sets the number of votes made.
	 * 
	 * @param voteCount
	 *            the number of votes made.
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
}
