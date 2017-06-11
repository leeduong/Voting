package com.leeduong.voting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;

/**
 * A resource controller handling requests for the candidates resource.
 * 
 * @author Lee Duong
 */
@RestController
public class CandidateController {
	private CandidateRepository candidateRepository;
	
	@Autowired
	public CandidateController(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	
	/**
	 * This method handles GET requests for /candidates.
	 * 
	 * @return a list of {@link Candidate}s.
	 */
    @RequestMapping(value = "/candidates", method = RequestMethod.GET, produces = "application/json")
    public List<Candidate> getCandidates() {
    	return candidateRepository.findAll();
    }
}