package com.example.fastnotes

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.fastnotes.db.NotesDbHelper
import com.example.fastnotes.model.Note

class DialogAddNewNote: DialogFragment() {

    private lateinit var notesDbHelper: NotesDbHelper
    private lateinit var titleNoteDialog: EditText
    private lateinit var descriptionNoteDialog: EditText

    companion object {
        const val DIALOG_TITLE = "Nova nota :D"
        const val DIALOG_POSITIVE_BTN = "Criar nota"
        const val DIALOG_NEGATIVE_BTN = "Voltar"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        notesDbHelper = NotesDbHelper(requireActivity().applicationContext)

        val inflater = requireActivity().layoutInflater;
        val viewDialog: View = inflater.inflate(R.layout.dialog_add_note_layout, null)
        setUi(viewDialog)

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(DIALOG_TITLE)
            .setView(viewDialog)
            .setPositiveButton(DIALOG_POSITIVE_BTN) { dialogInterface, i ->
                addNote()
            }
            .setNegativeButton(DIALOG_NEGATIVE_BTN) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
        return builder.create()
    }

    private fun addNote() {
        val title = titleNoteDialog.text.toString()
        val description = descriptionNoteDialog.text.toString()
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter required fields",
                Toast.LENGTH_LONG).show()
        } else {
            val noteModel = Note(title = title, description = description)
            notesDbHelper.saveNotes(noteModel)
        }
    }

    private fun setUi(view: View) {
        titleNoteDialog = view.findViewById(R.id.note_dialog_title)
        descriptionNoteDialog = view.findViewById(R.id.note_dialog_description)
    }
}