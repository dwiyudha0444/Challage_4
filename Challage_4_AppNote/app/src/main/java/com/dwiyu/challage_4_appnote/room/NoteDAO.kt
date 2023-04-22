package com.dwiyu.challage_4_appnote.room

import androidx.room.*

@Dao
interface NoteDAO {

    @Insert
    fun insertNote(note: DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id DESC ")
    fun getDataNote() : List<DataNote>

    @Query("SELECT * FROM DataNote ORDER BY id ASC ")
    fun getDataNoteASC() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote)

    @Update
    fun updateNote(note: DataNote)

}