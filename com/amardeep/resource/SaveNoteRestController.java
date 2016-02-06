package com.amardeep.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amardeep.dto.NoteDTO;
import com.amardeep.dto.StatusDTO;
import com.amardeep.service.UserService;

@Controller
@RequestMapping("pages")
public class SaveNoteRestController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="saveNote.do", method=RequestMethod.POST , headers="Accept=application/json")
	@ResponseBody
	public StatusDTO saveNote(@RequestBody NoteDTO note)
	{
		return userService.saveNote(note);
	}

}
