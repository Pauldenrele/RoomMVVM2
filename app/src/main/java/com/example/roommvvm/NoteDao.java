package com.example.roommvvm;


//Dao has to be interfaces or abstract class because we dont provide the method body we just create
//method and annotate it and room will automatically generate all the code for us

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

      @Insert
    void insert(Note note );

      @Update
    void  update (Note note);

      @Delete
    void delete (Note note);

      @Query("DELETE FROM note_table")
    void deleteAll();

      @Query("SELECT * FROM note_table ORDER BY priority DESC ")
    LiveData<List<Note>> getAllNote();



}
