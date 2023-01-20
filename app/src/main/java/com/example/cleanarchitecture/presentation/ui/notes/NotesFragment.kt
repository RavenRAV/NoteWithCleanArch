package com.example.cleanarchitecture.presentation.ui.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentNotesBinding
import com.example.cleanarchitecture.presentation.base.BaseFragment
import com.example.cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>(R.layout.fragment_notes) {

    override val binding by viewBinding(FragmentNotesBinding::bind)
    override val viewModel by viewModels<NotesViewModel>()

    override fun setupRequest() {
        viewModel.getAllNotes()
    }

    override fun setupSubscribers() {
        viewModel.getAllNotesState.collectState(
            onLoading = {},
            onSuccess = {})

        viewModel.deleteNoteState.collectState(
            onLoading = {},
            onSuccess = {
                Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
            })
    }




}