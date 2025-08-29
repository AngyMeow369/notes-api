package com.neeraj.notes_API.controller;

import com.neeraj.notes_API.model.Note;
import com.neeraj.notes_API.model.User;
import com.neeraj.notes_API.service.NoteService;
import com.neeraj.notes_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    // Create a note for authenticated user
    @PostMapping
    public ResponseEntity<Note> createNote(Authentication authentication, @RequestBody Note note) {
        User user = userService.findByUsername(authentication.getName());
        Note savedNote = noteService.createNoteForUser(note, user);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    // Get all notes for authenticated user
    @GetMapping
    public ResponseEntity<List<Note>> getNotes(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Note> notes = noteService.getNotesForUser(user);
        return ResponseEntity.ok(notes);
    }

    // Get note by ID (only if it belongs to authenticated user)
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(Authentication authentication, @PathVariable Integer id) {
        User user = userService.findByUsername(authentication.getName());
        Note note = noteService.getNoteByIdForUser(id, user);
        return ResponseEntity.ok(note);
    }

    // Update note by ID (only if it belongs to authenticated user)
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(Authentication authentication, @PathVariable Integer id, @RequestBody Note noteDetails) {
        User user = userService.findByUsername(authentication.getName());
        Note updatedNote = noteService.updateNoteForUser(id, noteDetails, user);
        return ResponseEntity.ok(updatedNote);
    }

    // Delete note by ID (only if it belongs to authenticated user)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(Authentication authentication, @PathVariable Integer id) {
        User user = userService.findByUsername(authentication.getName());
        noteService.deleteNoteForUser(id, user);
        return ResponseEntity.ok("Note deleted successfully");
    }
}

