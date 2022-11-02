package com.one0one.bma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.one0one.bma.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rlAddProduct.setOnClickListener{
            val intent = Intent(this, AddProductPage::class.java)
            startActivity(intent)
        }
        binding.rlSales.setOnClickListener{
            val intent = Intent(this, SalesPage::class.java)
            startActivity(intent)
        }

        binding.rlStockList.setOnClickListener{
            val intent = Intent(this, StockList::class.java)
            startActivity(intent)
        }

        binding.rlReceipt.setOnClickListener{
            val intent = Intent(this, ReceiptPage::class.java)
            startActivity(intent)
        }

        binding.rlInvoice.setOnClickListener{
            val intent = Intent(this, InvoicePage::class.java)
            startActivity(intent)
        }

        binding.rlAdmin.setOnClickListener{
            val intent = Intent(this, AdminPage::class.java)
            startActivity(intent)
        }
    }
}