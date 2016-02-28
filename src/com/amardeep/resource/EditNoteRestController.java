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
public class EditNoteRestController {

	@Autowired 
	UserService userService;
	
	@RequestMapping(value="editNote.do",method=RequestMethod.POST)
	@ResponseBody
	public StatusDTO editNote(@RequestBody NoteDTO note)
	{
		return userService.editNote(note);
	}
	
}
