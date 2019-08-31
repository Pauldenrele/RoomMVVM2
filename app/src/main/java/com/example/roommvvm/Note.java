package com.example.roommvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "note_table")
public class Note {
    //Room will automatically create columns for them

    @PrimaryKey(autoGenerate = true)

    private int id ;
    private  String Title;

    private String description;

    private int priority;

    //Set the id cos it is not in the constructor method

    public void setId(int id) {
        this.id = id;
    }


    //Constructor is needed in order to recreate from the database
      // ID not needed to be reconstructed

    public Note(String title, String description, int priority) {
        Title = title;
        this.description = description;
        this.priority = priority;
    }

    //Getter to get each
    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
