package com.one0one.bma

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.one0one.bma.DB.Items

class RvStockListAdapter: RecyclerView.Adapter<RvStockListAdapter.MyViewHolder>() {
    private var itemsList = emptyList<Items>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false))

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemsList[position]

        holder.itemView.findViewById<TextView>(R.id.tvItemStockList).text = currentItem.item
        holder.itemView.findViewById<TextView>(R.id.tvQtyStockList).text = currentItem.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.tvCostStockList).text = currentItem.bPrice.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setList(items: List<Items>){
        this.itemsList = items
        notifyDataSetChanged()
    }
}

