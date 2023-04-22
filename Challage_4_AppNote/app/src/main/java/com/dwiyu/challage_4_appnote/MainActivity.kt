package com.dwiyu.challage_4_appnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiyu.challage_4_appnote.databinding.ActivityMainBinding
import com.dwiyu.challage_4_appnote.room.DataNote
import com.dwiyu.challage_4_appnote.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var noteViewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NoteDB = NoteDatabase.getInstance(this)

        noteVm()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNoteObservers().observe(this, Observer {
            adapterNote.setNoteData(it as ArrayList<DataNote>)
        })

        binding.btnAddNote.setOnClickListener{
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    fun noteVm(){
        adapterNote = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapterNote
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }
    }
}