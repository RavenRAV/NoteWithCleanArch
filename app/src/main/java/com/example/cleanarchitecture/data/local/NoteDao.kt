package com.example.cleanarchitecture.data.local

import androidx.room.*
import com.example.cleanarchitecture.data.model.NoteEntity

@Dao
interface NoteDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes():List<NoteEntity>

    @Update
    suspend fun editNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}