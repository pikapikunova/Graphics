package com.example.graphics.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.graphics.MainActivity
import com.example.graphics.R
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

        val index1: Int
        val index2: Int
        val index3: Int

        val listOfColor =
            mutableMapOf("Красный" to 0xffff0000, "Чёрный" to 0xff000000, "Зелёный" to 0xff00ff00, "Синий" to 0xff0000ff)

        val fr2 = fragmentManager?.findFragmentById(R.id.startPage)

        if (fr2 != null) {
            if (fr2.editTextTextPersonName.text.toString() != "" && fr2.editTextTextPersonName2.text != null && fr2.editTextTextPersonName3.text != null && fr2.editTextTextPersonName4.text != null) {
                cartesianView.a.x1 = fr2.editTextTextPersonName.text.toString().toFloat()
                cartesianView.a.x2 = fr2.editTextTextPersonName2.text.toString().toFloat()
                cartesianView.a.y1 = fr2.editTextTextPersonName3.text.toString().toFloat()
                cartesianView.a.y2 = fr2.editTextTextPersonName4.text.toString().toFloat()

                index1 = fr2.radioGroup.checkedRadioButtonId
                if(fr2.radioGroup.findViewById<RadioButton>(index1).text == cartesianView.listOFFunc[0])
                {
                    cartesianView.ind = 1
                 }
                else
                    if(fr2.radioGroup.findViewById<RadioButton>(index1).text == cartesianView.listOFFunc[1])
                        cartesianView.ind = 2

                index2 = fr2.radioGroup2.checkedRadioButtonId
                for((key, value) in listOfColor) {
                    if (fr2.radioGroup2.findViewById<RadioButton>(index2).text == key) {
                        cartesianView.colorFunc = value.toInt()
                    }
                }

                index3 = fr2.radioGroup3.checkedRadioButtonId
                for((key, value) in listOfColor) {
                    if (fr2.radioGroup3.findViewById<RadioButton>(index3).text == key) {
                        cartesianView.colorCoord = value.toInt()
                    }
                }
            }


        }

        buttonStart.setOnClickListener {
            (activity as MainActivity?)?.openStartFragment()
        }


    }




}