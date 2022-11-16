package com.one0one.bma.Sales

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.one0one.bma.DB.Items
import com.one0one.bma.DB.ItemsViewModel
import com.one0one.bma.R
import com.one0one.bma.Stocklist.StockListAdapter
import com.one0one.bma.databinding.ActivitySalesPageBinding

class SalesPage : AppCompatActivity() {
    private lateinit var mItemsViewModel: ItemsViewModel
    private lateinit var binding: ActivitySalesPageBinding
    private lateinit var selectedIteemToSell: Items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView
        var adapter = StockListAdapter{
            selectedItem:Items -> itemClicked(selectedItem)
        }
        val itemsRecyclerView = binding.rvSalesPage
        itemsRecyclerView.adapter = adapter
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)

        //item view model
        mItemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        mItemsViewModel.getAllItems.observe(this) { items ->
            adapter.setStockList(items)
        }

    }
    private fun itemClicked(items: Items){
        selectedIteemToSell = items
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.salesquantity, null)
        val etSalesQuantity = dialogLayout.findViewById<EditText>(R.id.etItemQty)

        with(builder){
            setTitle("How Many ${selectedIteemToSell.item}")
            setPositiveButton("ADD"){dialog, which ->
                Toast.makeText(this@SalesPage,"The item clicked is : ${selectedIteemToSell.item}", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel"){dialog, which ->
                Log.d("Main", "Item was Canceled")
            }
            setView(dialogLayout)
            show()
        }

    }
}