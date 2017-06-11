package com.leeduong.voting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.Voter;
import com.leeduong.voting.domain.VoterRepository;

/**
 * A resource controller handling requests for the voters resource.
 * 
 * @author Lee Duong
 */
@RestController
public class VoterController {
	private VoterRepository voterRepository;

	@Autowired
	public VoterController(VoterRepository voterRepository) {
		this.voterRepository = voterRepository;
	}

	/**
	 * This method handles GET requests for /voters.
	 * 
	 * @return a list of {@link Voter}s.
	 */
	@RequestMapping(value = "/voters", method = RequestMethod.GET, produces = "application/json")
	public List<Voter> getVoters() {
		return voterRepository.findAll();
	}
}
