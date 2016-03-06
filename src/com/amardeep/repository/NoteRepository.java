package com.amardeep.repository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.amardeep.dto.NoteDTO;
import com.amardeep.dto.StatusDTO;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@Repository
public class NoteRepository {
	Logger log=Logger.getLogger(NoteRepository.class);
	
	public List<NoteDTO> getNotes()
	{
		List<NoteDTO> notes=new ArrayList<NoteDTO>();
		/*NoteDTO note=new NoteDTO();
		note.setNoteId("123");
		note.setNoteTitle("New Note");
		note.setNoteContent("Hello world");
		note.setNoteDate("10/01/2016");*/
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Note").addSort("noteServerDate", Query.SortDirection.DESCENDING);
	    List<Entity> entities = 
                      datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
		for(Entity entity:entities)
		{
			NoteDTO note=new NoteDTO();
			note.setNoteId(entity.getProperty("noteId").toString());
			if(entity.getProperty("noteTitle")!=null)
				note.setNoteTitle(entity.getProperty("noteTitle").toString());
			note.setNoteDate(entity.getProperty("noteDate").toString());
			if(entity.getProperty("noteContent")!=null)
				note.setNoteContent(entity.getProperty("noteContent").toString());
			if(entity.getProperty("flag")!=null)
				note.setFlag((Boolean)entity.getProperty("flag"));
			if(entity.getProperty("noteImage")!=null)
			{
				Blob blob=(Blob)entity.getProperty("noteImage");
				note.setNoteImage(new String(blob.getBytes()));
			}
				
			notes.add(note);
		}
		return notes;
	}
	public StatusDTO saveNote(NoteDTO note)
	{
		StatusDTO status=new StatusDTO();
		byte[] imageBytes=null;
		if(note!=null)
		{
		//generate random id
		/*Random random=new Random();
		int randId=random.nextInt(100000)+1;
		String noteId=String.valueOf(randId);
		if(noteId.length()<6)
		{
			int padd=6-noteId.length();
			for(int i=0;i<padd;i++)
			{
				noteId=noteId+"0";
			}
		}*/
		System.out.println(note.getNoteContent()+note.getNoteDate()+note.getNoteTitle()+note.getNoteId());
		//converting base64 encoded image string data to byte array
		if(note.getNoteImage()!=null){
			try {
				imageBytes =  note.getNoteImage().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//Using GAE datastore
		Key noteKey = KeyFactory.createKey("Note",note.getNoteId());
	    Entity noteEntity = new Entity("Note", noteKey);
	    noteEntity.setProperty("noteId",note.getNoteId());
	    noteEntity.setProperty("noteTitle",note.getNoteTitle() );
	    noteEntity.setProperty("noteContent",note.getNoteContent());
	    noteEntity.setProperty("noteDate", note.getNoteDate());
	    noteEntity.setProperty("flag", note.getFlag());
	    noteEntity.setProperty("noteServerDate",new Date());
	    if(note.getNoteImage()!=null)
	    	noteEntity.setProperty("noteImage", new Blob(imageBytes));
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    datastore.put(noteEntity);
		status.setNoteTitle(note.getNoteTitle());
		status.setStatus("Note saved successful!");
		return status;
		}
		else
		{
			status.setStatus("Failed to save note!");
			return status;
		}
	}
	public StatusDTO deleteNote(NoteDTO note)
	{
		StatusDTO status=new StatusDTO();
		System.out.println(note.getNoteTitle()+"title");
		status.setNoteTitle(note.getNoteTitle());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Note");
		query.addFilter("noteId", FilterOperator.EQUAL,note.getNoteId() );
		PreparedQuery pq = datastore.prepare(query);
		Entity noteEntity = pq.asSingleEntity();
		datastore.delete(noteEntity.getKey());
		status.setStatus("Note deleted!");
		return status;
	}
	public StatusDTO editNote(NoteDTO note)
	{
		StatusDTO status=new StatusDTO();
		byte[] imageBytes=null;
		status.setNoteTitle(note.getNoteTitle());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Note");
		query.addFilter("noteId", FilterOperator.EQUAL,note.getNoteId());
		PreparedQuery pq = datastore.prepare(query);
		Entity editedNote = pq.asSingleEntity();
		
		editedNote.setProperty("noteId", note.getNoteId());
		editedNote.setProperty("noteTitle",note.getNoteTitle());
		editedNote.setProperty("noteContent",note.getNoteContent());
		editedNote.setProperty("noteDate",note.getNoteDate());
		editedNote.setProperty("flag",true);
		editedNote.setProperty("noteServerDate", new Date());
		if(note.getNoteImage()!=null)
		{
			if(note.getNoteImage()!=null){
				try {
					imageBytes =  note.getNoteImage().getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			editedNote.setProperty("noteImage", new Blob(imageBytes));
		}
        datastore.put(editedNote);
        status.setStatus("Note edited!");
        return status;
	}

}
