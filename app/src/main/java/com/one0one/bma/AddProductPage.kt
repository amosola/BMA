package com.one0one.bma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.one0one.bma.DB.ItemDatabase
import com.one0one.bma.DB.Items
import com.one0one.bma.DB.ItemsViewModel
import com.one0one.bma.Stocklist.RvStockListAdapter
import com.one0one.bma.databinding.ActivityAddProductPageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddProductPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductPageBinding
    private lateinit var itemsDb: ItemDatabase
    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var adapter: RvStockListAdapter
    private lateinit var viewModel: ItemsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemsDb = ItemDatabase.getDatabase(this)


        binding.btnAddItems.setOnClickListener{
            saveItemsData()
            clearInputs()
        }

        binding.btnUpdateItem.setOnClickListener{
            val d =2
            readData(d)
        }
        binding.btnDeleteItem.setOnClickListener{
            val d =1
            readData(d)
        }

    }

    // saving data in database
    private fun saveItemsData(){
        val itemsName = binding.etAddItemsName.text.toString()
        val itemsQuantity = binding.etAddItemsQuantity.text.toString()
        val itemsBuyingPrice = binding.etAddItemsBuyingPrice.text.toString()
        val itemsSellingPrice = binding.etAddItemsSellingPrice.text.toString()

        //check the fields if they are not empty
        if (itemsName.isNotEmpty() && itemsQuantity.isNotEmpty() && itemsSellingPrice.isNotEmpty() && itemsBuyingPrice.isNotEmpty()){
            val item = Items(
                null,itemsName,itemsQuantity.toInt(),itemsBuyingPrice.toInt(),itemsSellingPrice.toInt()
            )
            GlobalScope.launch(Dispatchers.IO){
                itemsDb.ItemsDao().insertItems(item)
            }
            Toast.makeText(this@AddProductPage, "successful", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@AddProductPage, "Enter all field", Toast.LENGTH_SHORT).show()
        }
    }

    //Clearing all fields
    private fun clearInputs(){
        binding.etAddItemsName.text.clear()
        binding.etAddItemsQuantity.text.clear()
        binding.etAddItemsBuyingPrice.text.clear()
        binding.etAddItemsSellingPrice.text.clear()
        binding.etUpdateItems.text.clear()
    }


    //updating data
    private suspend fun updateData(items: Items){
        withContext(Dispatchers.Main){

            binding.etAddItemsName.setText(items.item)
            binding.etAddItemsQuantity.setText(items.quantity.toString())
            binding.etAddItemsBuyingPrice.setText(items.bPrice.toString())
            binding.etAddItemsSellingPrice.setText(items.sPrice.toString())

            //make buttons invisible
            binding.btnAddItems.visibility = View.INVISIBLE
            binding.btnUpdateItem.visibility = View.INVISIBLE
            binding.btnDeleteItem.visibility = View.INVISIBLE
            binding.etUpdateItems.visibility = View.INVISIBLE
            binding.btnChangeAndUpdateItems.visibility = View.VISIBLE

            // set on click listener
            binding.btnChangeAndUpdateItems.setOnClickListener {
                updatingData(items)
            }

        }
    }

    //reading data
    private fun readData(d:Int){
        val idNo = binding.etUpdateItems.text.toString()
        if (idNo.isNotEmpty()){
            lateinit var items: Items
            GlobalScope.launch {
                items = itemsDb.ItemsDao().findByIdNo(idNo.toInt())
                if (d ==2){
                    updateData(items)
                }else{
                    deleteData(items)
                }
            }
        }
    }

    // deleting items
    private fun deleteData(items: Items){

        GlobalScope.launch(Dispatchers.IO){

            itemsDb.ItemsDao().deleteItems(items)
        }
        clearInputs()
    }

    private fun updatingData(items: Items){
        val idNo = items.id
        val itemsName = binding.etAddItemsName.text.toString()
        val itemsQuantity = binding.etAddItemsQuantity.text.toString()
        val itemsBuyingPrice = binding.etAddItemsBuyingPrice.text.toString()
        val itemsSellingPrice = binding.etAddItemsSellingPrice.text.toString()

        //check the fields if they are not empty
        if (itemsName.isNotEmpty() && itemsQuantity.isNotEmpty() && itemsSellingPrice.isNotEmpty() && itemsBuyingPrice.isNotEmpty()){

            GlobalScope.launch(Dispatchers.IO){
                if (idNo != null) {
                    itemsDb.ItemsDao().update(itemsName,itemsQuantity.toInt(),itemsBuyingPrice.toInt(),itemsSellingPrice.toInt(), idNo)
                }
            }
            //make buttons visible again
            binding.btnAddItems.visibility = View.VISIBLE
            binding.btnUpdateItem.visibility = View.VISIBLE
            binding.btnDeleteItem.visibility = View.VISIBLE
            binding.etUpdateItems.visibility = View.VISIBLE
            binding.btnChangeAndUpdateItems.visibility = View.INVISIBLE
            clearInputs()
            Toast.makeText(this@AddProductPage, "successfully updated", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@AddProductPage, "Enter all field", Toast.LENGTH_SHORT).show()
        }
    }
}