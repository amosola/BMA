package com.one0one.bma.DB

import androidx.lifecycle.LiveData

class ItemsRepository(private val itemsDao: ItemsDao) {
    val getAllItems: LiveData<List<Items>> = itemsDao.getAllItems()

    suspend fun insertItems(items: Items){
        itemsDao.insertItems(items)
    }
    suspend fun updateItems(items: Items){
        itemsDao.updateItems(items)
    }
    suspend fun deleteItems(items: Items){
        itemsDao.deleteItems(items)
    }
}