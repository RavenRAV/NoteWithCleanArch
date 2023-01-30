package com.example.cleanarchitecture.presentation.ui.notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentNotesBinding
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.presentation.ui.adapters.NotesAdapter
import com.example.cleanarchitecture.presentation.ui.base.BaseFragment
import com.example.cleanarchitecture.presentation.extention.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>(R.layout.fragment_notes) {

    override val binding by viewBinding(FragmentNotesBinding::bind)
    override val viewModel by viewModels<NotesViewModel>()
    private val notesAdapter by lazy { NotesAdapter(this::onItemClick, this::onLongItemClick) }

    override fun initialize() {
        with(binding.notesRecycler) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = notesAdapter
        }

    }

    override fun setupRequest() {
        viewModel.getAllNotes()
    }

    override fun setupSubscribers() {
        viewModel.getAllNotesState.collectState(
            onError = {
            },
            onLoading = {
                binding.notesProgress.isVisible = true
            },
            onSuccess = {
                notesAdapter.submitList(it as ArrayList<Note>)
                binding.notesProgress.isVisible = false
            })
    }

    private fun onItemClick(note: Note) {
        val bundel = Bundle()
        bundel.putSerializable(NF_AEF_NOTE, note)
        findNavController().navigate(R.id.action_notesFragment_to_addEditFragment, bundel)
    }

    private fun onLongItemClick(note: Note){
        viewModel.deleteNote(note)
        viewModel.deleteNoteState.collectState(
            onError = {
                showToast(it)
                binding.notesProgress.isVisible = false
            },
            onLoading = {
                binding.notesProgress.isVisible = true
            },
            onSuccess = {
                showToast(R.string.deleted_successfully)
            })


    }

    override fun setupListeners() {
        binding.notesAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addEditFragment)
        }
    }

    companion object {
        const val NF_AEF_NOTE = "nfaefnote"
    }


}