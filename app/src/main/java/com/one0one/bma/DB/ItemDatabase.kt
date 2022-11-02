package com.one0one.bma.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Items :: class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun ItemsDao(): ItemsDao

    companion object{
        @Volatile
        private var INSTANCE : ItemDatabase? =null
        fun getDatabase(context: Context): ItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}