package com.example.fastnotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastnotes.NoteOptionsDeleteDialog
import com.example.fastnotes.R
import com.example.fastnotes.adapter.NoteRecyclerViewAdapter
import com.example.fastnotes.db.NotesDbHelper
import com.example.fastnotes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment: Fragment(), NoteRecyclerViewAdapter.OnItemClickListener {

    private lateinit var notesDbHelper: NotesDbHelper
    private lateinit var notesFormFragment: NoteFormFragment

    private lateinit var rvLayout: RecyclerView
    private lateinit var rvAdapter: NoteRecyclerViewAdapter
    private lateinit var fabNewNote: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.notes_fragment, container, false)
        setUi(view)
        setRecyclerView(view)
        fabNewNote.setOnClickListener {
            goToNoteFormFragment()
        }
        return view
    }

    private fun goToNoteFormFragment() {
        notesFormFragment = NoteFormFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment, notesFormFragment)
            .commit()
    }

    private fun setRecyclerView(view: View) {
        rvAdapter = NoteRecyclerViewAdapter(getNotes(), this)
        rvLayout.layoutManager = LinearLayoutManager(view.context)
        rvLayout.adapter = rvAdapter
    }

    override fun onItemClick(position: Int) {
        val noteId = getNotes()[position].id
        val noteDialog = NoteOptionsDeleteDialog(noteId)
        noteDialog.show(parentFragmentManager, "notesDialogOptions")
    }

    override fun onLongClick(position: Int): Boolean {
        val thisNote = getNotes()[position]
        val noteFormFragment = NoteFormFragment()
        val bundle = Bundle()
        bundle.putString("noteId", thisNote.id.toString())
        bundle.putString("noteTitle", thisNote.title)
        bundle.putString("noteDescription", thisNote.description)
        noteFormFragment.arguments = bundle
        goToNoteFormFragment()
        return true
    }

    private fun getNotes(): MutableList<Note> {
        notesDbHelper = NotesDbHelper(requireContext())
        return notesDbHelper.getAllNotes()
    }

    private fun setUi(view: View) {
        rvLayout = view.findViewById(R.id.rv_notes)
        fabNewNote = view.findViewById(R.id.fab_new_note)
        fabNewNote.setImageResource(R.drawable.ic_add)
    }
}