package com.sagul.wowo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagul.wowo.R
import com.sagul.wowo.databinding.RecyclerRowBinding

class recyclerAdapter(val listem:ArrayList<Model>):RecyclerView.Adapter<recyclerAdapter.CountryVh>() {
    class CountryVh(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVh {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryVh(binding)
    }

    override fun getItemCount(): Int {
        return listem.size
    }

    override fun onBindViewHolder(holder: CountryVh, position: Int) {
        holder.itemView.setBackgroundResource(R.drawable.diagram)
        holder.binding.countryRcy.text="Ülke: ${listem.get(position).countryName}"
        holder.binding.moneyRcy.text="Para Birimi: ${listem.get(position).money}"
        holder.binding.otelRcy.text="Otel: ${listem.get(position).otel}"
        holder.binding.foodRcy.text="Yemek: ${listem.get(position).food}"
        holder.binding.transportRcy.text="Ulaşım: ${listem.get(position).trans}"
        holder.binding.havaRcy.text="Hava Durumu: ${listem.get(position).weather}"
        holder.binding.recyclerImg.indir(listem.get(position).Urlway)
    }
    fun refresh(list:ArrayList<Model>){
        listem.clear()
        listem.addAll(list)
        notifyDataSetChanged()
    }
}