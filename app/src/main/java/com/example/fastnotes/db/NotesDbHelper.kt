package com.example.fastnotes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fastnotes.model.Note

class NotesDbHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Notes.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "note"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val SQL_CREATE_NOTES = """
        CREATE TABLE $TABLE_NAME (
        $COLUMN_ID INTEGER PRIMARY KEY,
        $COLUMN_TITLE TEXT,
        $COLUMN_DESCRIPTION TEXT)
        """
        const val SQL_DELETE_NOTES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(SQL_CREATE_NOTES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL(SQL_DELETE_NOTES)
        onCreate(p0)
    }

    fun saveNotes(noteModel: Note) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, noteModel.id)
        contentValues.put(COLUMN_TITLE, noteModel.title)
        contentValues.put(COLUMN_DESCRIPTION, noteModel.description)
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllNotes(): MutableList<Note> {
        val notesList: MutableList<Note> = mutableListOf();
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            db.execSQL(selectQuery)
            return mutableListOf()
        }

        var id: Long
        var title: String
        var description: String

        while (cursor.moveToNext()) {
            cursor.apply {
                id = this.getLong(this.getColumnIndex(COLUMN_ID))
                title = this.getString(this.getColumnIndex(COLUMN_TITLE))
                description = this.getString(this.getColumnIndex(COLUMN_DESCRIPTION))

                val note = Note(id, title, description)
                notesList.add(note)
            }
        }
        return notesList
    }
}