package com.leeduong.voting.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Vote;
import com.leeduong.voting.domain.Voter;
import com.leeduong.voting.domain.VoterRepository;
import com.leeduong.voting.service.VoteService;

/**
 * A class to test {@link VoteController}.
 * 
 * @author Lee Duong
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class VoteControllerTest {
	private VoteService voteService = Mockito.mock(VoteService.class);
	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new VoteController(voteService)).build();
	}
	
	@Test
	public void testVoteController() throws Exception {
		Vote vote = new Vote();
		vote.setVoterId(1L);
		vote.setCandidateId(1L);
		this.mockMvc.perform(post("/vote").content(this.json(vote)).contentType(contentType))
				.andExpect(status().isCreated());
		verify(voteService).vote(vote);
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
