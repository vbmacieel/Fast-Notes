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

class NoteOptionsDialog: DialogFragment() {

    private lateinit var notesDbHelper: NotesDbHelper

    companion object {
        const val DIALOG_TITLE = "Nova nota :D"
        const val DIALOG_POSITIVE_BTN = "Criar nota"
        const val DIALOG_NEGATIVE_BTN = "Voltar"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        notesDbHelper = NotesDbHelper(requireActivity().applicationContext)

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(DIALOG_TITLE)
            .setPositiveButton(DIALOG_POSITIVE_BTN) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setNegativeButton(DIALOG_NEGATIVE_BTN) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
        return builder.create()
    }
}