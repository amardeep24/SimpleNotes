package com.amardeep.service;

import java.util.List;

import com.amardeep.dto.NoteDTO;
import com.amardeep.dto.StatusDTO;


public interface UserService {
	public List<NoteDTO> getNotes();
	public StatusDTO saveNote(NoteDTO note);
	public StatusDTO deleteNote(NoteDTO note);
	public void editNote(NoteDTO note);
}
