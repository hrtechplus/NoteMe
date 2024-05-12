package com.hasindu.noteme

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.hasindu.noteme.database.NoteDatabase
import com.hasindu.noteme.repository.NoteRepository
import com.hasindu.noteme.viewModel.NoteViewModel
import com.hasindu.noteme.viewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupViewModel()

    }

    private  fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
         noteViewModel = ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]

    }
}