package com.example.cleanarchitecture.presentation.ui.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleanarchitecture.databinding.ItemNoteBinding
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.presentation.extention.convertLongToTime
import com.example.cleanarchitecture.presentation.extention.titlecaseFirstChar

class NotesAdapter(
    private val onClick: (Note) -> Unit,
    private val onLongClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private var list = ArrayList<Note>()

    fun submitList(list: ArrayList<Note>){
        this.list = list
        notifyDataSetChanged()
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


    inner class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title.titlecaseFirstChar()
            binding.tvDescription.text = note.description.titlecaseFirstChar()
            binding.tvTime.text = note.createAt.convertLongToTime()

            itemView.setOnClickListener {
                onClick.invoke(note)
            }

            itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
        builder.setTitle("Are you sure you want to delete this task?")

        builder.setPositiveButton("Yes", fun(_: DialogInterface, _: Int) {

            onLongClick.invoke(note)
            list.remove(note)
            notifyDataSetChanged()

        })

        builder.setNegativeButton("Cancel"){
                _, _ -> builder.setCancelable(true)
        }

        builder.create()
        builder.show()
                true
            }
        }
    }

}