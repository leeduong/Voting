package com.leeduong.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Vote;
import com.leeduong.voting.domain.Voter;
import com.leeduong.voting.domain.VoterRepository;
import com.leeduong.voting.exception.ExceededVotesException;
import com.leeduong.voting.exception.InvalidIdException;
import com.leeduong.voting.service.ResultService;
import com.leeduong.voting.service.VoteService;

/**
 * A resource controller handling requests for the vote resource.
 * 
 * @author Lee Duong
 */
@RestController
public class VoteController {
	private VoteService voteService;

	@Autowired
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}

	/**
	 * This method handles POST requests for /vote.
	 * 
	 * @param vote
	 *            a {@link Vote} containing vote information.
	 * @return a response entity.
	 */
	@RequestMapping(value = "/vote", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> vote(@RequestBody Vote vote) throws ExceededVotesException, InvalidIdException {
		voteService.vote(vote);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}