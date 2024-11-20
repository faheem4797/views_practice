package com.example.viewspractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(
    private val fruitsList: List<Fruit>,
    private val onClickListener: (Fruit) -> Unit,
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(fruitsList[position], onClickListener)

    }
}

class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruit, onClickListener: (Fruit) -> Unit) {
        val textViewName = view.findViewById<TextView>(R.id.tvName)
        textViewName.text = fruit.name

        view.setOnClickListener {
            onClickListener(fruit)
        }


    }


}