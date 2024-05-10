package com.hasindu.noteme.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hasindu.noteme.model.Note

@Database(entities = [Note::class], version = 1)
abstract class  NoteDatabase:RoomDatabase(){
    abstract fun getNoteDao():NoteDao
    companion object{
        @Volatile // changes ehema ikamanata pennana  one nisa meka danne

        // data base eka  heavy nisa singlleton pattern ekak use karnwa one welwata ganna
        private  var instance : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?:
        synchronized(LOCK){
            instance?:
            createDatabase(context).also{
                instance = it
            }
        }

            private fun createDatabase (context: Context){
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    name = "note_db"
                ).build() //return
            }
    }
}