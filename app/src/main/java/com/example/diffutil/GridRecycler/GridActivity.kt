package com.example.diffutil.GridRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diffutil.R
import com.example.diffutil.util.RandomColorGenerator
import kotlinx.android.synthetic.main.activity_grid.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GridActivity : AppCompatActivity() {

    private val viewModel by viewModels<GridViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val gridAdapter = GridAdapter(this)

        gridAdapter.setOnDelete {
            viewModel.removeItem(it)
        }

        gridRecycler.apply {
            adapter = gridAdapter
        }

        GlobalScope.launch {
            viewModel.gridItemList.collect {
                gridAdapter.asyncListDiffer.submitList(it)
            }
        }

        addRandom.setOnClickListener {
            viewModel.addItem(
                GridItem(
                    RandomColorGenerator.getRandomColor(),
                    System.currentTimeMillis(),
                    false
                )
            )
        }
    }
}