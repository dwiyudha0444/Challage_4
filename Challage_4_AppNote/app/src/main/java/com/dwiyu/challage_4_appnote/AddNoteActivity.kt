package com.dwiyu.challage_4_appnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dwiyu.challage_4_appnote.databinding.ActivityAddNoteBinding
import com.dwiyu.challage_4_appnote.room.DataNote
import com.dwiyu.challage_4_appnote.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    var dbNote: NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //        initiate db note
        dbNote =NoteDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener{
            addNote()
        }

    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note = binding.noteContent.text.toString()

            dbNote!!.noteDao().insertNote(DataNote(0,title, note))
        }
        finish()
    }

}