/**
 * Author: Amardeep Bhowmick
 */
package com.amardeep.simplenote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amardeep.simplenote.model.Note;
import com.amardeep.simplenote.model.Status;
import com.amardeep.simplenote.repository.NoteRepository;
import com.amardeep.simplenote.service.NoteService;

/**
 * @author AMARDEEP
 *
 */
@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public List<Note> getNotes() {
		return noteRepository.getNotes();
	}

	@Override
	public Note getNote(String noteId) {
		return null;
	}

	@Override
	public Status saveNote(Note note) {
		return noteRepository.saveNote(note);
	}

	@Override
	public Status updateNote(Note note) {
		return noteRepository.updateNote(note);
	}

	@Override
	public Status deleteNote(String noteId) {
		return noteRepository.deleteNote(noteId);
	}

}
