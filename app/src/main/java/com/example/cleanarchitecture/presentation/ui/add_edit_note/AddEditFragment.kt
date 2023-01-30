package com.example.cleanarchitecture.presentation.ui.add_edit_note

import android.os.Build
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentAddEditBinding
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.presentation.extention.convertLongToTime
import com.example.cleanarchitecture.presentation.ui.base.BaseFragment
import com.example.cleanarchitecture.presentation.ui.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class AddEditFragment :
    BaseFragment<FragmentAddEditBinding, AddEditViewModel>(R.layout.fragment_add_edit) {

    override val binding by viewBinding(FragmentAddEditBinding::bind)
    override val viewModel by viewModels<AddEditViewModel>()
    private var note: Note? = null

    override fun initialize() {
        val arguments = arguments
        if (arguments !=null){
            note = arguments.getSerializable(NotesFragment.NF_AEF_NOTE) as Note
        }
        if (note != null) {
            binding.addTitle.setText(note!!.title)
            binding.addDescription.setText(note!!.description)
        }
    }

    override fun setupListeners() {
        with(binding) {
            addButton.setOnClickListener {
                if (note == null) {
                    viewModel.createNote(
                        Note(
                            title = addTitle.text.toString(),
                            description = addDescription.text.toString(),
                            createAt = System.currentTimeMillis()
                        )
                    )
                } else {
                    viewModel.editNote(
                        Note(
                            id = note!!.id,
                            title = addTitle.text.toString(),
                            description = addDescription.text.toString(),
                            createAt = System.currentTimeMillis()
                        )
                    )

                }
            }
        }
    }

    override fun setupSubscribers() {
        viewModel.createNoteState.collectState(
            onLoading = {},
            onError = {},
            onSuccess = {
                findNavController().navigateUp()
            }
        )

        viewModel.editNoteState.collectState(
            onLoading = {},
            onError = {},
            onSuccess = {
                findNavController().navigateUp()
            }
        )
    }


}