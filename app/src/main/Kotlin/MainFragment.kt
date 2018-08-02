package com.example.yoshi.todo2
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yoshi.todo2.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.architecture.ext.sharedViewModel

/*  MVC/MVP - View
    MainViewModelからListを貰い､フィルターをかけて表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡  */
class MainFragment : Fragment() {
    private val vModel by sharedViewModel<MainViewModel>()
    private lateinit var mAdapter: RecyclerViewAdapter
    private lateinit var filteredList: MutableList<FilteredToDoItem>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.handler = vModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filteredList = vModel.getItemListWithTag("")
        mAdapter = RecyclerViewAdapter(mList = filteredList, vModel = vModel)
        val recyclerView = binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.initItemDragHelper(adapter = mAdapter, _recyclerView = recyclerView)
        main_fab.setOnClickListener { fabBtnView: View ->
            val navController = Navigation.findNavController(fabBtnView)
            val bundle = Bundle().apply { putInt("itemNumber", vModel.getItemList().size) }
            navController.navigate(R.id.action_launcher_home_to_detail, bundle)
        }
        performTag.setOnClickListener { v ->
            val filterStr = filterEdit.text.toString()
            val bundle = Bundle().apply {
                putString("tagString", filterStr)
             //   putString("dateString",dateStr)
            }
            val navController = Navigation.findNavController(v)
            navController.navigate(R.id.action_launcher_home_to_filteredFragment, bundle)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vModel.itemList.observe(this@MainFragment, Observer {
            mAdapter.setListOfAdapter(vModel.getItemListWithTag(""))
            mAdapter.notifyDataSetChanged()
        })
    }

    fun dispatchFilter(targetDate: String, filterStr: String): MutableList<FilteredToDoItem> {
        if (targetDate == "") {
            return vModel.getItemListWithTag(filterStr)
        } else {
            if (!isValidAsDate(targetDate)) return vModel.getItemListWithTag(filterStr)
            return vModel.getItemListWithTag(filterStr).filterByDate(targetDate)
        }
    }
}
