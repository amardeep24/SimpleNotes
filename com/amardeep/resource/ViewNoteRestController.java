package com.amardeep.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amardeep.dto.NoteDTO;
import com.amardeep.service.UserService;

@Controller
@RequestMapping("pages")
public class ViewNoteRestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="getNotes.do", method=RequestMethod.GET)
	@ResponseBody
	public List<NoteDTO> getNotes()
	{
		return userService.getNotes();
	}

}
