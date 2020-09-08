package com.example.hiltinjectionapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hiltinjectionapp.databinding.FragmentNoteDetailBinding
import com.example.hiltinjectionapp.model.Notes
import com.example.hiltinjectionapp.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsNoteFragment : Fragment() {

    private val noteVm: AddNoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNoteDetailBinding>(layoutInflater, R.layout.fragment_note_detail, container, false)
        val id = arguments?.getInt("ID")?:0
        println("Incoming ID $id")
        noteVm.noteById(id).observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.note = it
                println("Incoming data: $it")
            }else{
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}