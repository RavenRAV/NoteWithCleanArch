package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    infix fun deleteNote(note: Note) = noteRepository.deleteNote(note)
}