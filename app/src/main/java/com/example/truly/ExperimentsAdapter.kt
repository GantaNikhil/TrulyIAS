package com.example.truly

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.truly.databinding.ItemExperimentBinding

class ExperimentsAdapter : RecyclerView.Adapter<ExperimentsAdapter.MyViewHolder>() {
    private lateinit var context: Context
    private var data = ArrayList<ExperimentsModel>()
    private var onClickListener : ((ExperimentsModel)->Unit)? = null

    fun setData(context: Context, list : ArrayList<ExperimentsModel>) {
        this.context = context
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
        when (data[position].exp) {
            1 -> {
                holder.binding.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.image_1))
            }
            2 -> {
                holder.binding.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.image_2))
            }
            3 -> {
                holder.binding.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.image_3))
            }
            else -> {
                holder.binding.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.image_4))
            }
        }
        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(val binding: ItemExperimentBinding) : RecyclerView.ViewHolder(binding.root)

}