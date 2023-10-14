package com.example.filehandlingtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.filehandlingtask.databinding.ProfileViewBinding
import com.squareup.picasso.Picasso

class adapter(private val items:Array<userData>) : RecyclerView.Adapter<adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProfileViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.mail.text = items[position].mail
        holder.phone.text = items[position].phone.toString()
        Picasso.get().load(items[position].imgPath).fit().into(holder.image)


    }


    class ViewHolder (binding:ProfileViewBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.name
        val mail = binding.mail
        val phone = binding.phone
        val image = binding.image
    }
}