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
import com.hasindu.noteme.MainActivity
import com.hasindu.noteme.R
import com.hasindu.noteme.databinding.FragmentAddNoteBinding
import com.hasindu.noteme.model.Note
import com.hasindu.noteme.viewModel.NoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note) ,MenuProvider{


    private  var addNoteBinding :FragmentAddNoteBinding?=null
    private val binding get() = addNoteBinding!!


    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNoteView: View




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView =view
    }


    private fun saveNote(view:View){
        val nTitle = binding.addNoteTitle.text.toString().trim()
        val noteDescription = binding.addNoteTitle.text.toString().trim()

        if (nTitle.isNotEmpty()){
            val note =Note(0,nTitle,noteDescription)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context,"Note saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
            Toast.makeText(addNoteView.context,"Please Enter Note title",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       return when (menuItem.itemId){
           R.id.saveMenu-> {
               saveNote(addNoteView)
               true
           }
           else -> false
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding= null
    }

}