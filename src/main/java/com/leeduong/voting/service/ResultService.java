package com.leeduong.voting.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Result;

/**
 * A class that provides a method to retrieve the voting results.
 * 
 * @author Lee Duong
 *
 */
@Service
public class ResultService {
	private CandidateRepository candidateRepository;
	
	@Autowired
	public ResultService(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	
	/**
	 * Returns the results of the voting.
	 * 
	 * @return the results of the voting.
	 */
	public List<Result> result() {
		List<Result> results = new ArrayList<Result>();
		for (Candidate candidate : candidateRepository.findAll()) {
			results.add(new Result(candidate.getName(), candidate.getVotes()));
		}
		Collections.sort(results);
		return results;
	}
}