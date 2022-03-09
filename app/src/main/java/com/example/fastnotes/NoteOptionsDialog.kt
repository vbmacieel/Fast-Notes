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

class NoteOptionsDialog(id: Long): DialogFragment() {

    private lateinit var notesDbHelper: NotesDbHelper
    private var noteId = id;

    companion object {
        const val DIALOG_TITLE = "Apagar nota ??"
        const val DIALOG_POSITIVE_BTN = "Apagar"
        const val DIALOG_NEGATIVE_BTN = "Cancelar"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        notesDbHelper = NotesDbHelper(requireActivity().applicationContext)

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(DIALOG_TITLE)
            .setPositiveButton(DIALOG_POSITIVE_BTN) { dialogInterface, i ->
                notesDbHelper.deleteNote(noteId)
            }
            .setNegativeButton(DIALOG_NEGATIVE_BTN) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
        return builder.create()
    }
}