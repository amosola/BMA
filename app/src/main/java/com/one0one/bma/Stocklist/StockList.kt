package com.one0one.bma.Stocklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.one0one.bma.DB.Items
import com.one0one.bma.DB.ItemsViewModel
import com.one0one.bma.databinding.ActivityStockListBinding


class StockList : AppCompatActivity() {
    private lateinit var mItemsViewModel: ItemsViewModel
    private lateinit var binding: ActivityStockListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView
        val adapter = RvStockListAdapter()
        val itemsRecyclerView = binding.rvStockList
        itemsRecyclerView.adapter = adapter
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)

        //item view model
        mItemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        mItemsViewModel.getAllItems.observe(this, androidx.lifecycle.Observer { items ->
            adapter.setStockList(items)

        })
    }
}