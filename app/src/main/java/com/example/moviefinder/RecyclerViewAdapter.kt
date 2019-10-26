package com.example.moviefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.MovieDatabase.Search
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter(private val search: List<Search>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {

        return search.size

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind("${search[position].title} Production year: ${search[position].year}")
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(titleAndYear: String) {
            itemView.txtTitle.text = titleAndYear
            // itemView.setOnClickListener {  }
        }
    }
}