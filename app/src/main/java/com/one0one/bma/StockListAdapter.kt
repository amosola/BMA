package com.one0one.bma

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.one0one.bma.DB.Items

class StockListAdapter : RecyclerView.Adapter<StockListAdapter.StockListViewHolder>() {
    private var itemsList = emptyList<Items>()

    class StockListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        return StockListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stocklist, parent, false))
    }

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        val currentItem = itemsList[position]

        holder.itemView.findViewById<TextView>(R.id.tvCountStockList).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvItemStockList).text = currentItem.item
        holder.itemView.findViewById<TextView>(R.id.tvQtyStockList).text = currentItem.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.tvCostStockList).text = currentItem.sPrice.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setStockList(items: List<Items>){
        this.itemsList = items
        notifyDataSetChanged()
    }
}