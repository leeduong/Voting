package com.leeduong.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to throw when a voter attempts to vote more times than allowed.
 * 
 * @author Lee Duong
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Vote quota reached")
public class ExceededVotesException extends Exception {
}
