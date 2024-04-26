package com.example.ContactProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProductListAdapter(private val productItemLayout: Int) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var productList: List<Product>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val item = holder.item
        val item2 = holder.item2
        val item3 = holder.item3
        productList.let {
            item.text = it!![listPosition].contactName
            item2.text = it!![listPosition].contactPhone
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            productItemLayout, parent, false)
        return ViewHolder(view)
    }


    fun setContactList(contacts: List<Product>) {
        productList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (productList == null) 0 else productList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.contactName)
        var item2: TextView = itemView.findViewById(R.id.contactNumber)
        var item3: ImageButton = itemView.findViewById(R.id.deleteButton)
    }


}