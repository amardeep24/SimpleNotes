package com.amardeep.dto;

import java.util.Date;

public class NoteDTO
{
	private String noteId;
	
	private String noteTitle;
	
	private String noteContent;
	
	private String noteDate;
	
	private Date noteServerDate;
	
	private Boolean flag;
	
	private String noteImage;

	public String getNoteImage() {
		return noteImage;
	}

	public void setNoteImage(String noteImage) {
		this.noteImage = noteImage;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Date getNoteServerDate() {
		return noteServerDate;
	}

	public void setNoteServerDate(Date noteServerDate) {
		this.noteServerDate = noteServerDate;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

}
