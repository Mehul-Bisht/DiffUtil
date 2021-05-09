package com.example.diffutil.LinearRecycler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _noteList: MutableStateFlow<List<Note>> = MutableStateFlow(listOf())
    val noteList: StateFlow<List<Note>> get() = _noteList

    fun addNote(note: Note) =
        viewModelScope.launch{

        _noteList.value += note
    }

    fun deleteNote(index: Int) {
        val note = _noteList.value[index]
        _noteList.value -= note
    }

    fun updateNote(index: Int) {
        val note = _noteList.value[index]
        val updatedList = _noteList.value.toMutableList()
        updatedList[index] = Note(
            "updated: ${note.text}",
            System.currentTimeMillis(),
            note.isChecked
        )
        _noteList.value = updatedList
    }

    fun toggleCheck(index: Int) {
        val note = _noteList.value[index]
        val updatedList = _noteList.value.toMutableList()
        updatedList[index] = Note(
            note.text,
            System.currentTimeMillis(),
            !note.isChecked
        )
        _noteList.value = updatedList
    }
}