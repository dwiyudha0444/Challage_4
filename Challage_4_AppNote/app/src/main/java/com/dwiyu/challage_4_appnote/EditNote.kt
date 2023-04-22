package com.dwiyu.challage_4_appnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dwiyu.challage_4_appnote.databinding.ActivityEditNoteBinding
import com.dwiyu.challage_4_appnote.room.DataNote
import com.dwiyu.challage_4_appnote.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditNote : AppCompatActivity() {
    lateinit var binding : ActivityEditNoteBinding
    var dbNote: NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)


        var getDataNote = intent.getSerializableExtra("dataedit") as DataNote

        binding.editTitle.setText(getDataNote.title)
        binding.editNotee.setText(getDataNote.content)
        binding.idNote.setText(getDataNote.id.toString())

        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTitle.text.toString()
        var note = binding.editNotee.text.toString()

        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(DataNote(idNote, title, note))
            runOnUiThread {
                Toast.makeText(this@EditNote, "Dat berhasil di Edit", Toast.LENGTH_LONG)
                    .show()
            }
            finish()
        }
    }
}