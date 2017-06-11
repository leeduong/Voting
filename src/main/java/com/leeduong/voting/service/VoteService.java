package com.leeduong.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Vote;
import com.leeduong.voting.domain.Voter;
import com.leeduong.voting.domain.VoterRepository;
import com.leeduong.voting.exception.ExceededVotesException;
import com.leeduong.voting.exception.InvalidIdException;

/**
 * A class that provides a method to vote.
 * 
 * @author Lee Duong
 *
 */
@Service
public class VoteService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	private static final int MAX_VOTES = 3;

	@Autowired
	public VoteService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
	
	/**
	 * Processes a vote.
	 * 
	 * @param vote
	 *            a {@link Vote} object.
	 */
	public void vote(Vote vote) throws ExceededVotesException, InvalidIdException {
		if (!isVoterRegistered(vote.getVoterId()) || !isCandidateRegistered(vote.getCandidateId())) {
			throw new InvalidIdException();
		} else if (hasVoterReachedMaximumNumberOfVotes(vote.getVoterId())) {
			throw new ExceededVotesException();
		}
		writeVote(vote);
	}

	private boolean isVoterRegistered(long voterId) {
		List<Voter> voters = voterRepository.findById(voterId);
		return !voters.isEmpty();
	}

	private boolean hasVoterReachedMaximumNumberOfVotes(long voterId) {
		List<Voter> voters = voterRepository.findById(voterId);
		return (voters.get(0).getVoteCount() == MAX_VOTES);
	}

	private boolean isCandidateRegistered(long candidateId) {
		List<Candidate> candidates = candidateRepository.findById(candidateId);
		return !candidates.isEmpty();
	}

	private void writeVote(Vote vote) {
		voterRepository.updateVoteCount(vote.getVoterId(), MAX_VOTES);
		candidateRepository.updateVotes(vote.getCandidateId());
	}
}
