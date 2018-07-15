package com.example.yoshi.todo2

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "DetailFragment frag was called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment_detail_to_launcher_home, null))
        cancelBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment_detail_to_launcher_home, null))
    }

    companion object {
        @JvmStatic
        fun newInstance() = this
    }
}
