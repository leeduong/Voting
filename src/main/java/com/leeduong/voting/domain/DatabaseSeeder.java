package com.leeduong.voting.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A {@link CommandLineRunner} implementation to create voter and candidate records in the database.
 * 
 * @author Lee Duong
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	
	@Autowired
	public DatabaseSeeder(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
	
	public void run(String... args) throws Exception {
		List<Voter> voters = new ArrayList<Voter>();
		voters.add(new Voter(0));
		voters.add(new Voter(0));
		voterRepository.save(voters);
		
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates.add(new Candidate("A", 0));
		candidates.add(new Candidate("B", 0));
		candidates.add(new Candidate("C", 0));
		candidates.add(new Candidate("D", 0));
		candidateRepository.save(candidates);
				
		
	}

}
