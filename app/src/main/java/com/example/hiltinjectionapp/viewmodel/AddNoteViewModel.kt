package com.example.hiltinjectionapp.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.hiltinjectionapp.datasource.Repository
import com.example.hiltinjectionapp.model.Notes
import kotlinx.coroutines.launch

class AddNoteViewModel @ViewModelInject constructor(
    var noteRepository: Repository,
    @Assisted var savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var addedNoteIndex: MutableLiveData<Long>
    private lateinit var addedNoteApiValue: MutableLiveData<Int>
    private lateinit var listOfNotes: MutableLiveData<List<Notes>>
    private lateinit var singleNote: MutableLiveData<Notes>

    fun addNote(note: Notes):LiveData<Long>{
        addedNoteIndex = MutableLiveData()
        viewModelScope.launch {
            noteRepository.addNote(notes = note){index->
                addedNoteIndex.value = index
            }
        }
        return addedNoteIndex
    }

    fun noteList():LiveData<List<Notes>>{
        listOfNotes = MutableLiveData()
        viewModelScope.launch {
            noteRepository.getAllNotes { list->
                listOfNotes.value = list
            }
        }
        return listOfNotes
    }

    fun noteById(id: Int):LiveData<Notes>{
        singleNote = MutableLiveData()
        viewModelScope.launch {
            noteRepository.singleNote(id){notes ->
                singleNote.value = notes
            }
        }
        return singleNote
    }

    fun addApiNote(note: Notes):LiveData<Int>{
        addedNoteApiValue = MutableLiveData()
        viewModelScope.launch {
            noteRepository.addApiNote(notes = note){
                addedNoteApiValue.value = it
            }
        }
        return addedNoteApiValue
    }
}