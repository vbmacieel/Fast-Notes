package com.example.fastnotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastnotes.adapter.NoteRecyclerViewAdapter
import com.example.fastnotes.db.NotesDbHelper
import com.example.fastnotes.fragments.NoteFormFragment
import com.example.fastnotes.fragments.NotesFragment
import com.example.fastnotes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val notesFragment: NotesFragment = NotesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDefaultFragment()
    }

    private fun setDefaultFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment, notesFragment, "NoteFragment")
        fragmentTransaction.commit()
    }

}