package com.example.fastnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fastnotes.R
import com.example.fastnotes.model.Note

class NoteRecyclerViewAdapter(
    private val context: Context,
    private var notesList: MutableList<Note> = mutableListOf()):
    RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val rvNoteTitle: TextView = itemview.findViewById(R.id.note_title)
        val rvNoteDescription: TextView = itemview.findViewById(R.id.note_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.apply {
            rvNoteTitle.text = note.title
            rvNoteDescription.text = note.description
        }
    }

    override fun getItemCount(): Int = notesList.size
}