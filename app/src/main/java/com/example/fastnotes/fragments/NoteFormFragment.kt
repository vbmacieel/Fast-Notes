package com.example.fastnotes.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fastnotes.R
import com.example.fastnotes.db.NotesDbHelper
import com.example.fastnotes.model.Note

class NoteFormFragment: Fragment() {
    private lateinit var notesDbHelper: NotesDbHelper
    private lateinit var notesFragment: NotesFragment

    private lateinit var noteFormTitle: EditText
    private lateinit var noteFormDescription: EditText
    private lateinit var noteFormBtnAddNote: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_form_fragment, container, false)
        setUi(view)
        btnCreateNewNote()
        return view
    }

    private fun btnCreateNewNote() {
        noteFormBtnAddNote.setOnClickListener {
            val noteTitle = noteFormTitle.text.toString()
            val noteDescription = noteFormDescription.text.toString()
            if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            } else {
                addNewNote(noteTitle, noteDescription)
                notesFragment = NotesFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_fragment, notesFragment)
                    .commit()
            }
        }
    }

    private fun addNewNote(noteTitle: String, noteDescription: String) {
        notesDbHelper = NotesDbHelper(requireContext())
        val newNote = Note(title = noteTitle, description = noteDescription)
        notesDbHelper.saveNotes(newNote)
    }

    private fun setUi(view: View) {
        noteFormTitle = view.findViewById(R.id.notes_form_title)
        noteFormDescription = view.findViewById(R.id.notes_form_description)
        noteFormBtnAddNote = view.findViewById(R.id.notes_form_btn_new_note)
    }
}