package com.example.diffutil.GridRecycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutil.LinearRecycler.Note
import com.example.diffutil.R
import com.example.diffutil.util.RandomColorGenerator

class GridAdapter(
    private val context: Context
): RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    private var onDelete: ((Int) -> Unit)? = null

    fun setOnDelete(onItemDeleted: ((Int) -> Unit)?) {
        onDelete = onItemDeleted
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val background: View = itemView.findViewById(R.id.bg)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete)

        fun bind(gridItem: GridItem) {
            background.setBackgroundColor(gridItem.color)
        }
    }

    private val RECYCLER_COMPARATOR = object : DiffUtil.ItemCallback<GridItem>() {
        override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem) =
            oldItem.timestamp == newItem.timestamp

        override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem) =
            oldItem == newItem
    }

    val asyncListDiffer = AsyncListDiffer(this, RECYCLER_COMPARATOR)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.grid_item, parent, false)
    )
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {

        val gridItem = asyncListDiffer.currentList[position]
        holder.bind(gridItem)

        holder.deleteButton.setOnClickListener {
            onDelete?.let {
                 it(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onViewRecycled(holder: GridViewHolder) {
        super.onViewRecycled(holder)

        Log.d("recycled position ","${holder.adapterPosition}")
    }
}