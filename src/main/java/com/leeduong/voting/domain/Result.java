package com.leeduong.voting.domain;

/**
 * A class that represents a result.
 * 
 * @author Lee Duong
 */
public class Result implements Comparable<Result> {
	private String name;
	private long votes;
	
	public Result(String name, long votes) {
		this.name = name;
		this.votes = votes;
	}
	
	/**
	 * @return the candidate name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the number of votes for the candidate.
	 */
	public long getVotes() {
		return votes;
	}

	public int compareTo(Result result) {
		return (int) (result.getVotes() - this.votes);
	}

}
