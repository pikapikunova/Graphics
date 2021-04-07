package com.example.graphics.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.graphics.MainActivity
import com.example.graphics.R
import kotlinx.android.synthetic.main.start_page_fragment.*


class start_page : Fragment() {

    companion object {
        fun newInstance() = start_page()
    }

    private lateinit var viewModel: StartPageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.start_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StartPageViewModel::class.java)

        buttonMain.setOnClickListener {
            if (editTextTextPersonName.text.isEmpty() || editTextTextPersonName2.text.isEmpty() || editTextTextPersonName3.text.isEmpty() || editTextTextPersonName4.text.isEmpty()) {
                textView19.text = "Введите значения!"
            }
            else {
                if (editTextTextPersonName.text.toString().toFloatOrNull() == null
                    || editTextTextPersonName2.text.toString().toFloatOrNull() == null
                    || editTextTextPersonName3.text.toString().toFloatOrNull() == null
                    || editTextTextPersonName4.text.toString().toFloatOrNull() == null) {
                    textView19.text = "Введите числовые значения!"
                }
                else {
                    if(editTextTextPersonName.text.toString().toFloat() >=
                        editTextTextPersonName2.text.toString().toFloat() ||
                        editTextTextPersonName3.text.toString().toFloat() >=
                        editTextTextPersonName4.text.toString().toFloat()) {
                        textView19.text = "Такой промежуток невозможен!"
                    }
                    else {
                        if(editTextTextPersonName.text.toString().toFloat() <= -50 ||
                            editTextTextPersonName.text.toString().toFloat() >= 50 ||
                            editTextTextPersonName2.text.toString().toFloat() <= -50 ||
                            editTextTextPersonName2.text.toString().toFloat() >= 50 ||
                            editTextTextPersonName3.text.toString().toFloat() <= -50 ||
                            editTextTextPersonName3.text.toString().toFloat() >= 50 ||
                            editTextTextPersonName4.text.toString().toFloat() <= -50 ||
                            editTextTextPersonName4.text.toString().toFloat() >= 50) {
                            textView19.text = "Значения должны быть в пределах от -50 до 50!"
                        }
                        else
                        {
                            if (radioGroup.checkedRadioButtonId == -1 || radioGroup2.checkedRadioButtonId == -1 || radioGroup3.checkedRadioButtonId == -1) {
                                textView21.text = "Выберите значение настроек!"
                            } else
                                (activity as MainActivity?)?.openMainFragment()
                        }
                    }
                }
            }
        }

    }


    val APP_PREFERENCES_EDIT = listOf("editTextTextPersonName", "editTextTextPersonName2", "editTextTextPersonName3", "editTextTextPersonName4")
    val APP_PREFERENCES_Radio = listOf("radioGroup", "radioGroup2", "radioGroup3")


    override fun onPause() {
        super.onPause()

        val listOfVidgetsEdit = mutableListOf<EditText>(editTextTextPersonName,
            editTextTextPersonName2,
            editTextTextPersonName3,
            editTextTextPersonName4)

        val listOfVidgetsRadio =
            mutableListOf<RadioGroup>(
                radioGroup,
                radioGroup2,
                radioGroup3)

        // Запоминаем данные
        val editor = (activity as MainActivity?)?.prefs!!.edit()

        var i =0
        for(vidgEdit in listOfVidgetsEdit)
        {
            editor.putString(APP_PREFERENCES_EDIT[i], vidgEdit.text.toString()).apply()
            i+=1
        }
        i=0
        for(vidgRadio in listOfVidgetsRadio)
        {
            editor.putInt(APP_PREFERENCES_Radio[i], vidgRadio.checkedRadioButtonId).apply()
            i+=1
        }
    }

    override fun onResume() {
        super.onResume()

        val listOfVidgetsEdit = mutableListOf<EditText>(editTextTextPersonName,
            editTextTextPersonName2,
            editTextTextPersonName3,
            editTextTextPersonName4)

        val listOfVidgetsRadio =
            mutableListOf<RadioGroup>(
                radioGroup,
                radioGroup2,
                radioGroup3)

        var i =0
        for(vidgEdit in listOfVidgetsEdit)
        {
            if((activity as MainActivity?)?.prefs!!.contains(APP_PREFERENCES_EDIT[i])){
                vidgEdit.setText((activity as MainActivity?)?.prefs!!.getString(APP_PREFERENCES_EDIT[i], "0"))
                i+=1
            }
        }

        i=0

        for(vidgRadio in listOfVidgetsRadio)
        {
            if((activity as MainActivity?)?.prefs!!.contains(APP_PREFERENCES_Radio[i])){
                vidgRadio.check((activity as MainActivity?)?.prefs!!.getInt(APP_PREFERENCES_Radio[i], 0))
                i+=1
            }
        }

    }

}