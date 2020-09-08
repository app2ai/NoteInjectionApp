package com.example.hiltinjectionapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblNotes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "desc")
    val description: String,
    @ColumnInfo(name = "created_by")
    val createBy: Int,
    @ColumnInfo(name = "updated_by")
    val updatedBy: Int,
    @ColumnInfo(name = "created_date")
    val date: String,
    @ColumnInfo(name = "updated_date")
    val updateDate: String
)