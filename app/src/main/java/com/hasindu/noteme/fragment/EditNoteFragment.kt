package com.hasindu.noteme.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.hasindu.noteme.MainActivity
import com.hasindu.noteme.R
import com.hasindu.noteme.databinding.FragmentEditNoteBinding
import com.hasindu.noteme.model.Note
import com.hasindu.noteme.viewModel.NoteViewModel


class EditNoteFragment : Fragment(R.layout.fragment_edit_note),MenuProvider {

    private  var editNoteBinding :FragmentEditNoteBinding? = null

    private  val binding get() = editNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote : Note

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
                 savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost=requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote =args.note!!

        binding.editNoteTitle.setText(currentNote.nTitle)
        binding.editNoteTitle.setText(currentNote.nDescription)
        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteTitle.text.toString().trim()

            if (noteTitle.isNotEmpty()){
                val note =Note(currentNote.id,noteTitle,noteDesc)
                noteViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"Enter Note Titile",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private  fun deleteNote() {

        android.app.AlertDialog.Builder(activity).apply {

            setTitle("Delete Note")
            setMessage("You want Delete?")
            setPositiveButton("Delete"){_,_->
                noteViewModel.deleteNote(currentNote)
                Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)

            }
            setNegativeButton("Cancel",null)


        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        return  when (menuItem.itemId){
            R.id.deleteMenu->{
                deleteNote()
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

}

