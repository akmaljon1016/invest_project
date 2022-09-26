package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.developper.investproject.room_Model.Note
import com.developper.investproject.room_Model.Trade.Note_trade
import com.developper.investproject.room_Model.TradeDao

@Database(entities = [Note::class,Note_trade::class,], version = 4)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun noteTrade():TradeDao

    object DatabaseBuilder {
        private var INSTANCE: NoteDataBase? = null
        fun getDatabase(context: Context): NoteDataBase {
            if (INSTANCE == null) {
                synchronized(NoteDataBase::class.java) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NoteDataBase::class.java, "note_baza")
                .fallbackToDestructiveMigration()
                .build()
    }
}