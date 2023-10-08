package com.example.truly

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truly.databinding.ItemChapterBinding
import kotlin.math.exp

class ChaptersAdapter : RecyclerView.Adapter<ChaptersAdapter.MyViewHolder>() {
    private lateinit var context : Context
    private var data = ArrayList<ChaptersModel>()
    private var onClickListener : ((ExperimentsModel)->Unit)? = null

    fun setData(context : Context, list : ArrayList<ChaptersModel>) {
        this.context = context
        data.clear()
        data.addAll(list)
    }

    fun setOnClickListener(onClickListener: (ExperimentsModel)->Unit) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.chapterName.text = data[position].title

        val experiment = data[position].experiments

        val experimentsAdapter = ExperimentsAdapter()
        experimentsAdapter.setData(context,experiment)

        holder.binding.revHor.setHasFixedSize(true)
        holder.binding.revHor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // maybe get context from activity
        holder.binding.revHor.adapter = experimentsAdapter

        experimentsAdapter.setOnClickListener {
            onClickListener?.invoke(it)
        }

        holder.binding.root.setOnClickListener {
            if(holder.binding.revHor.visibility == View.VISIBLE)
                holder.binding.revHor.visibility = View.GONE
            else holder.binding.revHor.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = data.size

    inner class MyViewHolder(val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}