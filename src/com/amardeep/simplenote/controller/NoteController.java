/**
 * Author: Amardeep Bhowmick
 */
package com.amardeep.simplenote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	
	@RequestMapping(value="/getNotes", method=RequestMethod.GET)
	public @ResponseBody List<Note> getNotes() {
		return noteService.getNotes();

	}

	
	@RequestMapping(value="/getNote/{noteId}", method=RequestMethod.GET)
	public @ResponseBody Note getNote(@PathVariable("noteId") String noteId) {
		return noteService.getNote(noteId);
	}

	
	@RequestMapping(value="/saveNote", method=RequestMethod.POST)
	public @ResponseBody Status saveNote(@RequestBody Note note) {
		return noteService.saveNote(note);

	}

	@RequestMapping(value="/updateNote", method=RequestMethod.PUT)
	public @ResponseBody Status updateNote(@RequestBody Note note) {
		return noteService.updateNote(note);
	}

	@RequestMapping(value="/deleteNote/{noteId}", method=RequestMethod.DELETE)
	public @ResponseBody Status deleteNote(@PathVariable("noteId") String noteId)
	{
		return noteService.deleteNote(noteId);
	}
}
