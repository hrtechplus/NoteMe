package com.hasindu.noteme.model

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note")
@Parcelize // make it more simple data set
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nTitle :String,
    val nDescription :String
):Parcelable