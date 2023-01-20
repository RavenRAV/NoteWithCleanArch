package com.example.cleanarchitecture.presentation.ui.notes

import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.usecase.DeleteNoteUseCase
import com.example.cleanarchitecture.domain.usecase.GetAllNotesUseCase
import com.example.cleanarchitecture.presentation.base.BaseViewModel
import com.example.cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState = _getAllNotesState.asStateFlow()

    fun getAllNotes(){
        getAllNotesUseCase().collectFlow(_getAllNotesState)
    }

    fun deleteNote(note: Note){
        (deleteNoteUseCase deleteNote note).collectFlow(_deleteNoteState)
    }

}