package com.example.diffutil.LinearRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutil.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerAdapter =
            RecyclerAdapter(
                this,
                onDelete = { index ->
                    viewModel.deleteNote(index)
                },
                onEdit = { index ->
                    viewModel.updateNote(index)
                },
                onCheckToggle = { index ->
                    viewModel.toggleCheck(index)
                }
            )
        recyclerview.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = null
        }

        save.setOnClickListener {
            val text = layout.editText?.text.toString()
            if(!TextUtils.isEmpty(text)) {
                val note = Note(
                    text,
                    System.currentTimeMillis(),
                    false
                )
                viewModel.addNote(note)
                layout.editText?.setText("")
            }
        }

        GlobalScope.launch {
            viewModel.noteList.collect { notes ->
                withContext(Dispatchers.Main) {
                    recyclerAdapter.asyncListDiffer.submitList(notes)
                }
            }
        }
    }}