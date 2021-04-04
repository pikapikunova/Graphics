package com.example.graphics.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.graphics.MainActivity
import com.example.graphics.R
import com.example.graphics.ui.view.Translate
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.start_page_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val index: Int

        val fr2 = fragmentManager?.findFragmentById(R.id.startPage)

        if (fr2 != null) {
            if (fr2.editTextTextPersonName.text != null && fr2.editTextTextPersonName2.text != null && fr2.editTextTextPersonName3.text != null && fr2.editTextTextPersonName4.text != null) {
                cartesianView.xnach = fr2.editTextTextPersonName.text.toString().toFloat()
                cartesianView.xkonech = fr2.editTextTextPersonName2.text.toString().toFloat()
                cartesianView.ynach = fr2.editTextTextPersonName3.text.toString().toFloat()
                cartesianView.ykonech = fr2.editTextTextPersonName4.text.toString().toFloat()
                index = fr2.radioGroup.checkedRadioButtonId
                if(fr2.radioGroup.findViewById<RadioButton>(index).text == cartesianView.listOFFunc[0])
                {
                    cartesianView.ind = 1
                }
                else
                    if(fr2.radioGroup.findViewById<RadioButton>(index).text == cartesianView.listOFFunc[1])
                        cartesianView.ind = 2
            }
        }

        button.setOnClickListener {
            (activity as MainActivity?)?.openStartFragment()
        }


    }


}