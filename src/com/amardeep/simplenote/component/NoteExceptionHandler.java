package com.amardeep.simplenote.component;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amardeep.simplenote.exception.NoteNotFoundException;

@ControllerAdvice
public class NoteExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException noteNoteFoundException)
	{
		String message=noteNoteFoundException.getMessage();
		ResponseEntity<String> responseEntity=new  ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
		return responseEntity;
		
	}

}
