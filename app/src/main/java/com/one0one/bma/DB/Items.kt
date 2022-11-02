package com.one0one.bma.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Items(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "items_name") val item: String?,
    @ColumnInfo(name = "items_quantity") val quantity: Int?,
    @ColumnInfo(name = "items_bPrice") val bPrice: Int?,
    @ColumnInfo(name = "items_sPrice") val sPrice: Int?
)
