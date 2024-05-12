package com.hasindu.noteme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hasindu.noteme.databinding.NoteLayoutBinding
import com.hasindu.noteme.fragment.HomeFragmentDirections
import com.hasindu.noteme.model.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder> (){

    class NoteViewHolder(val itemBinding: NoteLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.nDescription == newItem.nDescription &&
                    oldItem.nTitle == newItem.nTitle
        }


        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       return  NoteViewHolder(
           NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNotes = differ.currentList[position]

        holder.itemBinding.noteTitle.text =currentNotes.nTitle
        holder.itemBinding.noteDesc.text =currentNotes.nDescription

        holder.itemView.setOnClickListener {

            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNotes)
            it.findNavController().navigate(direction)
        }
    }
}