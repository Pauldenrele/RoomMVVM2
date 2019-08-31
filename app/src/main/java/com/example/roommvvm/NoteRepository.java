package com.example.roommvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNote;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNote = noteDao.getAllNote();
    }

    public void insert (Note note){

        new InsertNoteasynctask(noteDao).execute(note);


    }
    public void update (Note note){
        new UpdateNoteasynctask(noteDao).execute(note);


    }
    public  void delete (Note note){

        new deleteNoteasynctask(noteDao).execute(note);


    }

    public void deleteAll(){
        new deleteAllNoteasynctask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNote() {
        return allNote;
    }

    /*AsyncTask enables proper and easy use of the UI thread. This class allows to perform background
   operations and publish results on the UI thread without having to manipulate threads and or handlers.
    */
    private static  class  InsertNoteasynctask extends AsyncTask<Note , Void , Void>{

        //It cant be accessed directly cos it static
        private  NoteDao noteDao;

        private InsertNoteasynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static  class  UpdateNoteasynctask extends AsyncTask<Note , Void , Void>{

        //It cant be accessed directly cos it static
        private  NoteDao noteDao;

        private UpdateNoteasynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static  class  deleteNoteasynctask extends AsyncTask<Note , Void , Void>{

        //It cant be accessed directly cos it static
        private  NoteDao noteDao;

        private deleteNoteasynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static  class  deleteAllNoteasynctask extends AsyncTask<Void , Void , Void>{

        //It cant be accessed directly cos it static
        private  NoteDao noteDao;

        private deleteAllNoteasynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
