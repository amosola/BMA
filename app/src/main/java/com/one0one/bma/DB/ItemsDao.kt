package com.one0one.bma.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.one0one.bma.DB.Items
import org.jetbrains.annotations.NotNull

@Dao
interface ItemsDao{
    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(items: Items)

    @NotNull
    @Delete
    suspend fun deleteItems(items: Items)

    @NotNull
    @Update
    suspend fun updateItems(items: Items)


    @Query("SELECT * FROM items_table")
    fun getAllItems(): LiveData<List<Items>>

    @Query("UPDATE items_table SET items_name=:itemsName, items_quantity=:itemsQuantity, " +
            "items_bprice=:itemsBuyingPrice, items_sprice=:itemsSellingPrice WHERE id=:idNo")
    suspend fun update(itemsName: String,itemsQuantity: Int, itemsBuyingPrice: Int, itemsSellingPrice:Int, idNo:Int)

    @Query("SELECT * FROM items_table WHERE id LIKE :idNo LIMIT 1")
    suspend fun findByIdNo(idNo: Int): Items
}