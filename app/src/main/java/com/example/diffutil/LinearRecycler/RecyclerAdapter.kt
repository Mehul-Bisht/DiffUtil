package com.example.diffutil.LinearRecycler

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
import com.example.diffutil.R

class RecyclerAdapter(
    private val context: Context,
    private val onDelete: (Int) -> Unit,
    private val onEdit: (Int) -> Unit,
    private val onCheckToggle: (Int) -> Unit
): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val noteText: TextView = itemView.findViewById(R.id.noteText)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete)
        val editButton: ImageView = itemView.findViewById(R.id.edit)
        val checkBox: ImageView = itemView.findViewById(R.id.checkBox)

        fun bind(note: Note) {
            noteText.text = note.text
        }
    }

    private val RECYCLER_COMPARATOR = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem.timestamp == newItem.timestamp

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem
    }

    val asyncListDiffer = AsyncListDiffer(this, RECYCLER_COMPARATOR)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)
    )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val note = asyncListDiffer.currentList[position]
        holder.bind(note)

        if(note.isChecked) {
            holder.checkBox.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check))
        } else {
            holder.checkBox.setImageDrawable(context.resources.getDrawable(R.drawable.ic_uncheck))
        }

        holder.deleteButton.setOnClickListener {
            onDelete(holder.adapterPosition)
        }

        holder.editButton.setOnClickListener {
            onEdit(holder.adapterPosition)
        }

        holder.checkBox.setOnClickListener {
            onCheckToggle(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onViewRecycled(holder: RecyclerViewHolder) {
        super.onViewRecycled(holder)

        Log.d("recycled position ","${holder.adapterPosition}")
    }
}