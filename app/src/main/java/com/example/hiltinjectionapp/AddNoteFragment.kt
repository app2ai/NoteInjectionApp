package com.example.hiltinjectionapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hiltinjectionapp.constants.Constants.Companion.SUCCESS_RESPONSE
import com.example.hiltinjectionapp.model.Notes
import com.example.hiltinjectionapp.utils.isInternetConnected
import com.example.hiltinjectionapp.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_note_fragment.view.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.add_note_fragment, container, false)

        v.buttonSave.setOnClickListener{
            if (v.editTextTitle.text.isEmpty() || v.editTextDesc.text.isEmpty()){
                Toast.makeText(context, "Please, fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                val note = Notes(null, v.editTextTitle.text.toString(), v.editTextDesc.text.toString(), 1, 1, "", "")
                if (isInternetConnected(context)){
                    viewModel.addApiNote(note = note).observe(viewLifecycleOwner, Observer {msg->
                        msg.let {
                            Toast.makeText(context, msg.messageText, Toast.LENGTH_SHORT).show()
                            if (it.messageId != SUCCESS_RESPONSE) {
                                addOffline(note)
                            }
                        }
                    })
                }else{
                    addOffline(note)
                }
            }
        }
        return v
    }

    private fun addOffline(notes: Notes){
        viewModel.addNote(note = notes)
            .observe(viewLifecycleOwner, Observer {
                // TODO: Change this in future and provide proper information
                Toast.makeText(context, "LocalDB Response value $it", Toast.LENGTH_SHORT).show()
            })
    }
}