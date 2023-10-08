package com.example.truly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.truly.databinding.ItemExperimentBinding

class ExperimentsAdapter : RecyclerView.Adapter<ExperimentsAdapter.MyViewHolder>() {
    private var data = ArrayList<ExperimentsModel>()
    private var onClickListener : ((ExperimentsModel)->Unit)? = null

    fun setData(list : ArrayList<ExperimentsModel>) {
        data.clear()
        data.addAll(list)
    }

    fun setOnClickListener(onClickListener : (ExperimentsModel)->Unit) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExperimentsAdapter.MyViewHolder {
        val binding = ItemExperimentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExperimentsAdapter.MyViewHolder, position: Int) {
        holder.binding.expName.text = data[position].expName
        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(val binding: ItemExperimentBinding) : RecyclerView.ViewHolder(binding.root)

}