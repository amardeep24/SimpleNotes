package com.amardeep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amardeep.dto.NoteDTO;
import com.amardeep.dto.StatusDTO;
import com.amardeep.repository.NoteRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public List<NoteDTO> getNotes() {
		
		return noteRepository.getNotes();
	}

	@Override
	public StatusDTO saveNote(NoteDTO note) {
		return noteRepository.saveNote(note);
	}

	@Override
	public StatusDTO deleteNote(NoteDTO note) {
		return noteRepository.deleteNote(note);
	}

	@Override
	public void editNote(NoteDTO note) {
		noteRepository.editNote(note);
	}

}
