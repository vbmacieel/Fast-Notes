package com.example.fastnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastnotes.adapter.NoteRecyclerViewAdapter
import com.example.fastnotes.db.NotesDbHelper
import com.example.fastnotes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var notesDbHelper: NotesDbHelper

    private lateinit var rvLayout: RecyclerView
    private lateinit var rvAdapter: NoteRecyclerViewAdapter
    private lateinit var fabNewNote: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUi()
        setRecyclerView()

        val dialogNewNotes = DialogAddNewNote()
        fabNewNote.setOnClickListener {
            dialogNewNotes.show(supportFragmentManager, "newNotes")
        }
    }

    private fun setRecyclerView() {
        rvAdapter = NoteRecyclerViewAdapter(this, getNotes())
        rvLayout.layoutManager = LinearLayoutManager(this)
        rvLayout.adapter = rvAdapter
    }

    private fun getNotes(): MutableList<Note> {
        notesDbHelper = NotesDbHelper(this)
        return notesDbHelper.getAllNotes()
    }

    private fun setUi() {
        rvLayout = findViewById(R.id.rv_notes)
        fabNewNote = findViewById(R.id.fab_new_note)
        fabNewNote.setImageResource(R.drawable.ic_add)
    }
}