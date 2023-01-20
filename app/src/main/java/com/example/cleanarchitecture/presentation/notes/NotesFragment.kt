package com.example.cleanarchitecture.presentation.notes

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
import com.example.cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val binding by viewBinding(FragmentNotesBinding::bind)
    private val viewModel by viewModels<NotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNotes()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllNotesState.collect {state->
                    when(state){
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Loading -> {

                        }
                        is UIState.Success -> {

                        }
                    }
                }
            }
        }
    }

}