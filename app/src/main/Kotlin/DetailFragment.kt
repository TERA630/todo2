package com.example.yoshi.todo2
import android.app.DatePickerDialog
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
import com.example.yoshi.todo2.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.architecture.ext.sharedViewModel

/*  MVC/MVP - View
    データ変更を伴うユーザーの入力イベントは　Databinding使用  */
class DetailFragment : Fragment() {
    private val vModel by sharedViewModel<MainViewModel>()
    private lateinit var stashItem: ToDoItem
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val itemNumber = arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            safeArgs.itemNumber
        } ?: vModel.getItemList().size
        binding.item = if (itemNumber <= vModel.getItemList().lastIndex) {
            stashCurrentItem(itemNumber)
            vModel.getItemList()[itemNumber]
        } else {

            ToDoItem("enter new item ${itemNumber + 1}")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemNumber = arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            safeArgs.itemNumber
        } ?: vModel.getItemList().size

        // itemNumber<= アイテム数は編集モード､　itemNumber = アイテム数は追加モード

        if (itemNumber <= vModel.getItemList().lastIndex) {
            applyBtn.setOnClickListener { v: View ->
                vModel.modifyItem(itemNumber, titleTxt.editableText.toString())
                val navController = Navigation.findNavController(v)
                navController.navigate(R.id.action_fragment_detail_to_launcher_home)
            }
            cancelBtn.setOnClickListener { view: View ->
                popStashedItem(itemNumber)
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.action_fragment_detail_to_launcher_home)
            }
        } else {
            titleTxt.setText("enter new item")
            applyBtn.setOnClickListener { v: View ->
                vModel.appendItem(titleTxt.editableText.toString())
                val navController = Navigation.findNavController(v)
                navController.navigate(R.id.action_fragment_detail_to_launcher_home)
            }
            cancelBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment_detail_to_launcher_home))
        }
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

    private fun stashCurrentItem(itemNumber: Int) {
        stashItem = vModel.getItemList()[itemNumber]

    }

    private fun popStashedItem(itemNumber: Int) {
        vModel.getItemList()[itemNumber] = stashItem
        Log.i("test", "item ${itemNumber + 1} was reverted")
    }

    companion object {
        @JvmStatic
        fun newInstance(): DetailFragment {
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