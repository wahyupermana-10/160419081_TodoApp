package com.ubaya.a160419081_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419081_todoapp.R
import com.ubaya.a160419081_todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


class EditTodoFragment : Fragment() {
    private lateinit var viewModel:DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        txtJudulTodo.text = "Edit Todo"
        btnCreate.text = "Save Changes"

        btnCreate.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriorty.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
        }

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)

            when (it.priority) {
                3 -> radioHigh.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioLow.isChecked = true
            }
        })
    }
}