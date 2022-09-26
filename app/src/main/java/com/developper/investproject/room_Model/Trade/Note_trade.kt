package com.developper.investproject.room_Model.Trade

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "trade_table")
@Parcelize
data class Note_trade(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val summa: Int,
    val telegram_id: String,
    val time: String,
) : Parcelable {

}
