package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes() = noteRepository.getAllNotes()
}