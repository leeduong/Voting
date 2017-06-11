package com.leeduong.voting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leeduong.voting.domain.Candidate;
import com.leeduong.voting.domain.CandidateRepository;
import com.leeduong.voting.domain.Result;
import com.leeduong.voting.service.ResultService;

/**
 * A resource controller handling requests for the results resource.
 * 
 * @author Lee Duong
 */
@RestController
public class ResultController {
	private ResultService resultService;
	
	@Autowired
	public ResultController(ResultService resultService) {
		this.resultService = resultService;
	}
	
	/**
	 * This method handles GET requests for /result.
	 * 
	 * @return a list of {@link Candidate}s.
	 */
    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    public List<Result> result(){
    	return resultService.result();
    }
}
