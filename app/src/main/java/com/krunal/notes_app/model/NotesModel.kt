package com.krunal.notes_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NotesModel(
    @ColumnInfo var title: String?,
    @ColumnInfo var text: String?,
    @ColumnInfo var category: String?,
    @ColumnInfo var images: String?,
    @ColumnInfo var dateTime: String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
