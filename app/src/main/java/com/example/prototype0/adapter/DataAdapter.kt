package com.example.prototype0.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.prototype0.EditActivity
import com.example.prototype0.MainActivity
import com.example.prototype0.R
import com.example.prototype0.model.DataModel
import kotlinx.android.synthetic.main.item_data_layout.view.*

class DataAdapter(private val context: Context): RecyclerView.Adapter<DataAdapter.UserViewHolder>() {

    private var dataList = emptyList<DataModel>()

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.textItemDataId.text = dataList[position].itemDataId
        holder.itemView.textItemData0.text = dataList[position].itamData0
        holder.itemView.textItemData1.text = dataList[position].itamData1
        holder.itemView.setOnClickListener{
            Toast.makeText(context, dataList[position].toString(), Toast.LENGTH_SHORT).show()
            val i = Intent(holder.itemView.context, EditActivity::class.java)
                .putExtra("key", dataList[position].itemDataId)//id
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DataModel>){
        dataList = list
        notifyDataSetChanged()
    }

}