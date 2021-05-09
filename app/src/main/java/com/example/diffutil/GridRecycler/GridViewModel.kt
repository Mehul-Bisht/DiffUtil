package com.example.diffutil.GridRecycler

import androidx.lifecycle.ViewModel
import com.example.diffutil.LinearRecycler.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GridViewModel: ViewModel() {

    private val _gridItemList: MutableStateFlow<List<GridItem>> = MutableStateFlow(listOf())
    val gridItemList: StateFlow<List<GridItem>> get() = _gridItemList

    fun addItem(gridItem: GridItem) {
        _gridItemList.value += gridItem
    }

    fun removeItem(index: Int) {
        _gridItemList.value -= _gridItemList.value[index]
    }
}