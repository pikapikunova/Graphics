package com.example.graphics.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        button2.setOnClickListener {
            (activity as MainActivity?)?.openMainFragment()
        }

    }





}