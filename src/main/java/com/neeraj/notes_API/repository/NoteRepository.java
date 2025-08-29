package com.neeraj.notes_API.repository;

import com.neeraj.notes_API.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findByUserId(Integer userId);


}
