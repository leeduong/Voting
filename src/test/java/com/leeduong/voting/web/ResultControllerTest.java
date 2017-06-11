package com.leeduong.voting.web;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.service.ResultService;
import com.leeduong.voting.service.VoteService;

/**
 * A class to test {@link ResultController}.
 * 
 * @author Lee Duong
 */
public class ResultControllerTest {
	private ResultService resultService = Mockito.mock(ResultService.class);
	private MockMvc mockMvc;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new ResultController(resultService)).build();
	}

	@Test
	public void testResultController() throws Exception {
		this.mockMvc.perform(get("/result").contentType(contentType))
				.andExpect(status().isOk());
		verify(resultService).result();
	}
}
