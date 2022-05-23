package com.example.infrastudy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.infrastudy.databinding.RowBinding

class Adapter(var items:ArrayList<GetPostResponse>) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    inner class ViewHolder(val binding:RowBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.textView6.setOnClickListener {
                clickeListener?.Clicked(items[adapterPosition])
            }
        }
    }
    var clickeListener:OnItemClickListener?=null

    interface OnItemClickListener{
        public fun Clicked(item : GetPostResponse)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView6.text=items[position].title
        holder.binding.textView5.text="author : "+items[position].userid
    }
    override fun getItemCount(): Int {
        return items.size
    }
}