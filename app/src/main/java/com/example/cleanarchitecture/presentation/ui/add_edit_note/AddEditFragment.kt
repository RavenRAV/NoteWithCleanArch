package com.example.cleanarchitecture.presentation.ui.add_edit_note

import android.os.Build
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentAddEditBinding
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class AddEditFragment :
    BaseFragment<FragmentAddEditBinding, AddEditViewModel>(R.layout.fragment_add_edit) {

    override val binding by viewBinding(FragmentAddEditBinding::bind)
    override val viewModel by viewModels<AddEditViewModel>()

    override fun setupListeners() {
        with(binding) {
            addButton.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    viewModel.createNote(
                        Note(
                            title = addTitle.text.toString(),
                            description = addDescription.text.toString(),
                            createAt = LocalDate.now().toString()
    //                        with(Calendar.getInstance()){
    //                            "${get(Calendar.DAY_OF_MONTH)} ${get(Calendar.MONTH)+1} ${get(Calendar.YEAR)} "
    //                        }
    //                        System.currentTimeMillis()
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
    }


}