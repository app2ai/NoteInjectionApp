package com.example.hiltinjectionapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
                val note = Notes(null, v.editTextTitle.text.toString(), v.editTextDesc.text.toString(), 1, 1, "03-09-2020", "03-09-2020")
                if (isInternetConnected(context)){
                    viewModel.addApiNote(note = note).observe(viewLifecycleOwner, Observer {
                        // TODO: Change this in future and provide proper information
                        Toast.makeText(context, "Response value $it", Toast.LENGTH_SHORT).show()
                    })
                }else{
                    viewModel.addNote(note = note)
                        .observe(viewLifecycleOwner, Observer {
                            // TODO: Change this in future and provide proper information
                            Toast.makeText(context, "LocalDB Response value $it", Toast.LENGTH_SHORT).show()
                        })
                    }
                }
        }
        return v
    }
}