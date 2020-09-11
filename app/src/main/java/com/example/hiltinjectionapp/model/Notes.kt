package com.example.hiltinjectionapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.time.Instant

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
    @ColumnInfo(name = "created_date", defaultValue = "CURRENT_TIMESTAMP")
    @SerializedName("createdDate")
    val date: String?,
    @ColumnInfo(name = "updated_date",defaultValue = "CURRENT_TIMESTAMP")
    val updateDate: String?
)