package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_filtered.*
import org.koin.android.architecture.ext.sharedViewModel

class FilteredFragment : Fragment() {
    private val vModel by sharedViewModel<MainViewModel>()
    //  private lateinit var mFilteredAdapter:RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filtered, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tagString = arguments?.let {
            val safeArgs = FilteredFragmentArgs.fromBundle(it)
            safeArgs.tagString
        } ?: "home"
        val dateString = arguments?.let{
            val safeDate = FilteredFragmentArgs.fromBundle(it)
            safeDate
        }

        val filteredList = vModel.getItemListWithTag(tagString)
        val mFilteredAdapter = RecyclerViewAdapter(filteredList, vModel)
        val recyclerView = recycler_viewOfFilter
        recyclerView.adapter = mFilteredAdapter
        recyclerView.setHasFixedSize(true)
    }
}



