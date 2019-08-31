package com.example.roommvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class} , version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    /*
    This variable is created bcos we hve to turn the class into a singleton
    Singleton means we cant create multiple instance of the database instead we are to use the same instance
    in our app which we can then access over this static variable
    */
    private static NoteDatabase instance;

    /*
    We will later use this method to access our Dao then we dont have to create a body bcos room
    takes care of other code
    */
    public abstract NoteDao noteDao();



    //A singleton to get the NoteDatabase
    public static synchronized  NoteDatabase getInstance(Context context){
        if (instance== null){
            instance = Room.databaseBuilder(context.getApplicationContext() ,NoteDatabase.class ,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return  instance;

    }


    private static  RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAysncTask(instance).execute();
        }
    };

    private static class  populateDbAysncTask extends AsyncTask<Void , Void , Void>{

        private NoteDao noteDao;

        private populateDbAysncTask(NoteDatabase db){
            db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1 " , "Desc 1" , 1));
            noteDao.insert(new Note("Title 2 " , "Desc 2" , 2));
            noteDao.insert(new Note("Title 3 " , "Desc 3" , 3));
            return null;
        }
    }


}
