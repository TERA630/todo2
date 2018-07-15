package com.example.yoshi.todo2

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject


class DetailFragment : Fragment() {
    val viewModel by inject<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "DetailFragment frag was called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemNumber = arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            safeArgs.itemNumber
        } ?: 0

        titleTxt.setText("To do is $itemNumber")
        applyBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment_detail_to_launcher_home, null))
        cancelBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment_detail_to_launcher_home, null))

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val startDataSetListener = DataSetListener(startDateTxt)
        val deadDataSetListener = DataSetListener(deadDateTxt)

        startCal.setOnClickListener {
            val startDatePicker = DatePickerDialog(context, startDataSetListener, year, monthOfYear, dayOfMonth)
            startDatePicker.show()
        }
        deadCal.setOnClickListener {
            val deadDatePicker = DatePickerDialog(context, deadDataSetListener, year, monthOfYear, dayOfMonth)
            deadDatePicker.show()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(thisDate: String): DetailFragment {
            val bundle = Bundle()
            val newFragment = DetailFragment()
            newFragment.arguments = bundle
            return newFragment
        }
    }

    class DataSetListener(private val _textView: TextView) : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            _textView.text = "$year/${month + 1}/$dayOfMonth"
        }
    }

}
