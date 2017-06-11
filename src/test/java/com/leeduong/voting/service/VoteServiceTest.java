package com.leeduong.voting.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Vote;
import com.leeduong.voting.domain.Voter;
import com.leeduong.voting.domain.VoterRepository;
import com.leeduong.voting.exception.ExceededVotesException;
import com.leeduong.voting.exception.InvalidIdException;


/**
 * A class to test {@link VoteService}.
 * 
 * @author Lee Duong
 */
public class VoteServiceTest {
	private VoterRepository voterRepository = Mockito.mock(VoterRepository.class);
	private CandidateRepository candidateRepository = Mockito.mock(CandidateRepository.class);
	private VoteService voteService = new VoteService(voterRepository, candidateRepository);
	private static final long VALID_CANDIDATE_ID = 13L;
	private static final long VALID_VOTER_ID = 3L;
	private static final long VALID_VOTER_ID_WITH_VOTE_QUOTA_REACHED = 4L;
	private static final int MAX_VOTES_ALLOWED = 3;
	
	@Before
	public void setup() {
		List<Voter> votersforValidVoterId = new ArrayList<Voter>();
		votersforValidVoterId.add(new Voter());
		when(voterRepository.findById(VALID_VOTER_ID)).thenReturn(votersforValidVoterId);

		List<Candidate> candidatesforValidCandidateId = new ArrayList<Candidate>();
		candidatesforValidCandidateId.add(new Candidate());
		when(candidateRepository.findById(VALID_CANDIDATE_ID)).thenReturn(candidatesforValidCandidateId);
		
		List<Voter> votersforValidVoterIdWithVoteLimitReached = new ArrayList<Voter>();
		votersforValidVoterIdWithVoteLimitReached.add(new Voter(MAX_VOTES_ALLOWED));
		when(voterRepository.findById(VALID_VOTER_ID_WITH_VOTE_QUOTA_REACHED))
				.thenReturn(votersforValidVoterIdWithVoteLimitReached);
	}
	
	@Test(expected = InvalidIdException.class)
	public void testVoteServiceWithInvalidVoterId() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(1L);
		vote.setCandidateId(VALID_CANDIDATE_ID);
		voteService.vote(vote);
	}

	@Test(expected = InvalidIdException.class)
	public void testVoteServiceWithInvalidCandidateId() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(VALID_VOTER_ID);
		vote.setCandidateId(10L);
		voteService.vote(vote);
	}

	@Test
	public void testVoteControllerWithValidVoterIdAndCandidateId() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(VALID_VOTER_ID);
		vote.setCandidateId(VALID_CANDIDATE_ID);
		voteService.vote(vote);
		verify(voterRepository).updateVoteCount(VALID_VOTER_ID, MAX_VOTES_ALLOWED);
		verify(candidateRepository).updateVotes(VALID_CANDIDATE_ID);
	}

	@Test
	public void testVoteControllerWithMultipleVotes() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(VALID_VOTER_ID);
		vote.setCandidateId(VALID_CANDIDATE_ID);
		voteService.vote(vote);
		voteService.vote(vote);
		verify(voterRepository, times(2)).updateVoteCount(VALID_VOTER_ID, MAX_VOTES_ALLOWED);
		verify(candidateRepository, times(2)).updateVotes(VALID_CANDIDATE_ID);
	}

	@Test(expected = ExceededVotesException.class)
	public void testVoteControllerWithMaximumVotesExceeded() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(VALID_VOTER_ID_WITH_VOTE_QUOTA_REACHED);
		vote.setCandidateId(VALID_CANDIDATE_ID);
		voteService.vote(vote);
	}
}
