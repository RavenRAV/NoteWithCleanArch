package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.base.BaseRepository
import com.example.cleanarchitecture.data.local.NoteDao
import com.example.cleanarchitecture.data.mappers.toNote
import com.example.cleanarchitecture.data.mappers.toNoteEntity
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.repository.NoteRepository
import com.example.cleanarchitecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository, BaseRepository() {

    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toNoteEntity())
    }

    override fun getAllNotes()= doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.toNoteEntity())
    }

    override fun deleteNote(note: Note)= doRequest {
        noteDao.deleteNote(note.toNoteEntity())
    }
}