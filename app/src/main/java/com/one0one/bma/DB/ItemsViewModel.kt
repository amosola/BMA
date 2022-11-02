package com.one0one.bma.DB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.one0one.bma.DB.ItemDatabase
import com.one0one.bma.DB.Items
import com.one0one.bma.DB.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    val getAllItems: LiveData<List<Items>>
    private val repository: ItemsRepository
    init {
        val dao = ItemDatabase.getDatabase(application).ItemsDao()
        repository = ItemsRepository(dao)
        getAllItems = repository.getAllItems
    }

    fun insertItems(items: Items) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItems(items)
    }

    fun updateItems(items: Items) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateItems(items)
    }
    fun deleteItems(items: Items) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItems(items)
    }
}