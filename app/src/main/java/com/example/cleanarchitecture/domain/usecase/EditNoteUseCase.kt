package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: Note) = noteRepository.editNote(note)
}