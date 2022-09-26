package com.developper.investproject.room_Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize

data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val summa: Int,
    val telegram_id: String,
    val time: String,
) : Parcelable {
}
