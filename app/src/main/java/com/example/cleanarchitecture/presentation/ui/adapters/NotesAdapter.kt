package com.example.cleanarchitecture.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleanarchitecture.databinding.ItemNoteBinding
import com.example.cleanarchitecture.domain.model.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private var list = listOf<Note>()

    fun submitList(list: List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description
            binding.tvTime.text = note.createAt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}