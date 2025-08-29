package com.neeraj.notes_API.service;

import com.neeraj.notes_API.model.Note;
import com.neeraj.notes_API.model.User;
import com.neeraj.notes_API.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepo;

    // Create note for authenticated user
    public Note createNoteForUser(Note note, User user) {
        note.setUser(user);
        return noteRepo.save(note);
    }

    // Get all notes for authenticated user
    public List<Note> getNotesForUser(User user) {
        return noteRepo.findByUserId(user.getId());
    }

    // Get note by ID only if it belongs to user
    public Note getNoteByIdForUser(Integer noteId, User user) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with id: " + noteId)
                );
        if (!note.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: not your note");
        }
        return note;
    }

    // Update note only if it belongs to user
    public Note updateNoteForUser(Integer noteId, Note noteDetails, User user) {
        Note note = getNoteByIdForUser(noteId, user);
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepo.save(note);
    }

    // Delete note only if it belongs to user
    public void deleteNoteForUser(Integer noteId, User user) {
        Note note = getNoteByIdForUser(noteId, user);
        noteRepo.delete(note);
    }
}



