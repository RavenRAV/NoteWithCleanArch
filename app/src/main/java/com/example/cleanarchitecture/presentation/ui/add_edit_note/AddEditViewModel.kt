package com.example.cleanarchitecture.presentation.ui.add_edit_note

import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.usecase.CreateNoteUseCase
import com.example.cleanarchitecture.domain.usecase.EditNoteUseCase
import com.example.cleanarchitecture.presentation.base.BaseViewModel
import com.example.cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
): BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note: Note){
        createNoteUseCase(note).collectFlow(_createNoteState)
    }

    fun editNote(note: Note){
        editNoteUseCase(note).collectFlow(_editNoteState)
    }
}