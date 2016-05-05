/**
 * Author: Amardeep Bhowmick
 */
package com.amardeep.simplenote.service;

import java.util.List;

import com.amardeep.simplenote.exception.NoteNotFoundException;
import com.amardeep.simplenote.model.Note;
import com.amardeep.simplenote.model.Status;

/**
 * @author AMARDEEP
 *
 */
public interface NoteService {
	public List<Note> getNotes();
	public Note getNote(String noteId)throws NoteNotFoundException;
	public Status saveNote(Note note);
	public Status updateNote(Note note);
	public Status deleteNote(String noteId)throws NoteNotFoundException;
}
