/**
 * Author: Amardeep Bhowmick
 */
package com.amardeep.simplenote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amardeep.simplenote.exception.NoteNotFoundException;
import com.amardeep.simplenote.model.Note;
import com.amardeep.simplenote.model.Status;
import com.amardeep.simplenote.service.NoteService;

/**
 * @author AMARDEEP
 *
 */
@Controller
public class NoteController {
	@Autowired
	NoteService noteService;

	@RequestMapping(value = "/getNotes", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getNotes() {
		ResponseEntity<List<Note>> allNotes = new ResponseEntity<List<Note>>(
				noteService.getNotes(), HttpStatus.FOUND);
		return allNotes;

	}

	@RequestMapping(value = "/getNote/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<Note> getNote(@PathVariable("noteId") String noteId)
			throws NoteNotFoundException {
		ResponseEntity<Note> note = new ResponseEntity<Note>(
				noteService.getNote(noteId), HttpStatus.FOUND);
		return note;
	}

	@RequestMapping(value = "/saveNote", method = RequestMethod.POST)
	public ResponseEntity<Status> saveNote(@RequestBody Note note) {
		ResponseEntity<Status> status = new ResponseEntity<Status>(
				noteService.saveNote(note), HttpStatus.CREATED);
		return status;

	}

	@RequestMapping(value = "/updateNote", method = RequestMethod.PUT)
	public ResponseEntity<Status> updateNote(@RequestBody Note note) {
		ResponseEntity<Status> status = new ResponseEntity<Status>(
				noteService.updateNote(note), HttpStatus.CREATED);
		return status;
	}

	@RequestMapping(value = "/deleteNote/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Status> deleteNote(@PathVariable("noteId") String noteId)
			throws NoteNotFoundException {
		ResponseEntity<Status> status = new ResponseEntity<Status>(
				noteService.deleteNote(noteId), HttpStatus.GONE);
		return status;
	}
}
