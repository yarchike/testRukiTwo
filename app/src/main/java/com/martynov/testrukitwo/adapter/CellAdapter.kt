package com.martynov.testrukitwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.martynov.testrukitwo.R
import com.martynov.testrukitwo.model.Cell
import com.martynov.testrukitwo.model.State

class CellAdapter (val listCell: MutableList<Cell>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return CellViewHolder(this, cellView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cellIndex = position
        when (holder) {
            is CellViewHolder -> holder.bind(listCell[cellIndex])
        }
    }

    override fun getItemCount(): Int {
        return listCell.size
    }
    class CellViewHolder(val adapter: CellAdapter, view: View) : RecyclerView.ViewHolder(view){
        fun bind(cell: Cell){
            val imageViewState = itemView.findViewById<ImageView>(R.id.imageViewState)
            val textViewState = itemView.findViewById<TextView>(R.id.textViewState)
            val textViewMessange = itemView.findViewById<TextView>(R.id.textViewMessange)
            when(cell.state){
                State.LIVE ->{
                    loadImage(imageViewState, R.drawable.live)
                    textViewState.text = "Живая"
                    textViewMessange.text = "и шивелится!"
                }
                State.DEAD ->{
                    loadImage(imageViewState, R.drawable.dead)
                    textViewState.text = "Мёртвая"
                    textViewMessange.text = "или прикидывается"

                }
                State.LIFE -> {
                    loadImage(imageViewState, R.drawable.life)
                    textViewState.text = "Жизнь"
                    textViewMessange.text = "Ку-ку"
                }

            }

        }
        private fun loadImage(photoImg: ImageView, id: Int) {
            Glide.with(photoImg.context)
                .load(id)
                .into(photoImg)
        }

    }
}