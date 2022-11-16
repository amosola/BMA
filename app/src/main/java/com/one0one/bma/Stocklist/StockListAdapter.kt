package com.one0one.bma.Stocklist

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.one0one.bma.DB.Items
import com.one0one.bma.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class StockListAdapter(private val clickListener:(Items)->Unit) : RecyclerView.Adapter<StockListViewHolder>() {
    private val itemsList = ArrayList<Items>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val stock_list = layoutInflater.inflate(R.layout.stocklist, parent, false)
        return StockListViewHolder(stock_list)
    }

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        holder.bind(itemsList[position], clickListener)
       /*
        val currentItem = itemsList[position]

        holder.itemView.findViewById<TextView>(R.id.tvCountStockList).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvItemStockList).text = currentItem.item
        holder.itemView.findViewById<TextView>(R.id.tvQtyStockList).text = currentItem.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.tvCostStockList).text = currentItem.sPrice.toString()

        holder.itemView.setOnClickListener {
            //clickListener()
        }

        */
        /*
        holder.itemView.setOnClickListener{

                CoroutineScope(Dispatchers.Main).launch {
                    lateinit var inflater: LayoutInflater
                    lateinit var editText: EditText

                    val builder: AlertDialog.Builder = AlertDialog.Builder(it.context)

                    val dialogLayout : View? = inflater.inflate(R.layout.salesquantity, null)
                    if (dialogLayout != null) {
                        editText = dialogLayout.findViewById(R.id.etItemQty)
                    }


                    with(builder){
                        setTitle("Enter Amount of: ${currentItem.item} ")
                        setPositiveButton("OK"){dialog, which ->
                            //some action to be done
                           // val itm = editText.text.toString()
                            Toast.makeText(it.context, "Selected item is: itm", Toast.LENGTH_SHORT).show()
                        }
                        setNegativeButton("Cancel"){dialog, which ->
                            // some action to be done
                            Toast.makeText(it.context, "Selected item is Canceled", Toast.LENGTH_SHORT).show()
                        }
                        setView(dialogLayout)
                        show()
                    }
                }


            Toast.makeText(it.context, "Selected item is: itm", Toast.LENGTH_SHORT).show()
        }

                */
    }


    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setStockList(items: List<Items>){
        //this.itemsList = items
        itemsList.clear()
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }
}
class StockListViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(items: Items,clickListener:(Items)->Unit){
        var itemId = itemView.findViewById<TextView>(R.id.tvCountStockList).text
        var itemName = itemView.findViewById<TextView>(R.id.tvItemStockList).text
        var itemQuantity = itemView.findViewById<TextView>(R.id.tvQtyStockList).text
        var itemPrice = itemView.findViewById<TextView>(R.id.tvCostStockList).text

        //initialize variables
        itemId = items.id.toString()
        itemName = items.item
        itemQuantity = items.quantity.toString()
        itemPrice = items.sPrice.toString()

        itemView.setOnClickListener {
            clickListener(items)
        }

    }
}