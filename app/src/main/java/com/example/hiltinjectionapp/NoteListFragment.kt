package com.example.hiltinjectionapp

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltinjectionapp.constants.Constants.Companion.SUCCESS_RESPONSE
import com.example.hiltinjectionapp.model.Notes
import com.example.hiltinjectionapp.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.android.synthetic.main.fragment_note_list.view.*

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vr = inflater.inflate(R.layout.fragment_note_list, container, false)
        vr.fab.setOnClickListener {
            findNavController().navigate(R.id.action_NoteListFragment_to_addNoteFragment)
        }
        vr.noteRecycler.apply {
            setHasFixedSize(true)
        }
        syncedCall()
        return vr
    }

    private fun addedList(){
        viewModel.noteList().observe(viewLifecycleOwner, Observer { list->
            println("Coming data: $list")
            noteRecycler.adapter = NoteAdapter(list)
        })
    }

    private fun syncedCall(){
        Log.i("FRAGMENT", "Sync call")
        viewModel.syncNotes().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.messageText, Toast.LENGTH_SHORT).show()
            addedList()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        addedList()
    }

    class NoteAdapter(private var list: List<Notes>?) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

        class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
            val tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
            val card : CardView = itemView.findViewById(R.id.card)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
            return NoteHolder(v)
        }

        override fun getItemCount(): Int {
            return list?.size?:0
        }

        override fun onBindViewHolder(holder: NoteHolder, position: Int) {
            val o = list?.get(position)
            holder.tvTitle.text = o?.title
            holder.tvDesc.text = o?.description
            holder.card.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("ID", o?.id?:0)
                it.findNavController().navigate(R.id.action_NoteListFragment_to_NoteDetailFragment, bundle)
            }
        }
    }
}