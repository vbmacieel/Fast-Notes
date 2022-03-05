package com.example.fastnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastnotes.adapter.NoteRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
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
        rvAdapter = NoteRecyclerViewAdapter(this, mutableListOf())
        rvLayout.layoutManager = LinearLayoutManager(this)
        rvLayout.adapter = rvAdapter
    }

    private fun setUi() {
        rvLayout = findViewById(R.id.rv_notes)
        fabNewNote = findViewById(R.id.fab_new_note)
        fabNewNote.setImageResource(R.drawable.ic_add)
    }
}