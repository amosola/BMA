package com.one0one.bma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.one0one.bma.DB.ItemsViewModel
import com.one0one.bma.Stocklist.StockListAdapter
import com.one0one.bma.databinding.ActivitySalesPageBinding

class SalesPage : AppCompatActivity() {
    private lateinit var mItemsViewModel: ItemsViewModel
    private lateinit var binding: ActivitySalesPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView
        val adapter = StockListAdapter()
        val itemsRecyclerView = binding.rvSalesPage
        itemsRecyclerView.adapter = adapter
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)

        //item view model
        mItemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        mItemsViewModel.getAllItems.observe(this, androidx.lifecycle.Observer { items ->
            adapter.setStockList(items)

        })

    }
}