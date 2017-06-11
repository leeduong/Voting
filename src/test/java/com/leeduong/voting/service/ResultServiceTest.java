package com.leeduong.voting.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Result;

/**
 * A class to test {@link ResultService}.
 * 
 * @author Lee Duong
 */
public class ResultServiceTest {
	private CandidateRepository candidateRepository = Mockito.mock(CandidateRepository.class);
	private ResultService resultService = new ResultService(candidateRepository);
	
	@Before
	public void setup() {
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates.add(new Candidate("A", 2L));
		candidates.add(new Candidate("B", 6L));
		candidates.add(new Candidate("C", 4L));
		when(candidateRepository.findAll()).thenReturn(candidates);
	}
	
	@Test
	public void testResultService() throws Exception {
		List<Result> results = resultService.result();
		assertThat(results.size(), is(3));
		Result resultOne = results.get(0);
		Result resultTwo = results.get(1);
		Result resultThree = results.get(2);
		assertThat(resultOne.getName(), is("B"));
		assertThat(resultOne.getVotes(), is(6L));
		assertThat(resultTwo.getName(), is("C"));
		assertThat(resultTwo.getVotes(), is(4L));
		assertThat(resultThree.getName(), is("A"));
		assertThat(resultThree.getVotes(), is(2L));
	}
}
