package com.developper.investproject.room_Model

import androidx.room.*
import com.developper.investproject.room_Model.Trade.Note_trade

@Dao
interface TradeDao {
    @Insert
    fun insertNote(noteTrade: Note_trade)

    @Delete
    fun deleteNote(noteTrade: Note_trade)

    @Update
    fun updateNote(noteTrade: Note_trade)

    @Query("SELECT * FROM trade_table")
    fun getAllNotes(): List<Note>

    @Insert
    fun insertTrade(noteTrade: Note_trade)
}