package com.leeduong.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to throw when an id that does not exist in the system is provided.
 * 
 * @author Lee Duong
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid id")
public class InvalidIdException extends Exception {

}
