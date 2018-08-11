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
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val filteredList = vModel.getItemListCurrentWithTag(vModel.currentDateStr, vModel.tagFilters[0])
        mAdapter = RecyclerViewAdapter(mList = filteredList, vModel = vModel)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.handler = vModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.initItemDragHelper(adapter = mAdapter, _recyclerView = recyclerView)
        main_fab.setOnClickListener { fabBtnView: View ->
            val navController = Navigation.findNavController(fabBtnView)
            val args = DetailFragmentArgs.Builder(vModel.getItemList().size).build().toBundle()
            navController.navigate(R.id.action_launcher_home_to_detail,args)
        }
        performTag.setOnClickListener { v ->
            vModel.tagFilters[0] = filterEdit.text.toString()
            val navController = Navigation.findNavController(v)
            navController.navigate(R.id.action_launcher_home_to_filteredFragment)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vModel.itemList.observe(this@MainFragment, Observer {
            mAdapter.setListOfAdapter(vModel.getItemListCurrentWithTag(vModel.currentDateStr, vModel.tagFilters[0]))
            mAdapter.notifyDataSetChanged()
        })
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("filterDate", vModel.currentDateStr)
        outState.putString("tagString", vModel.tagFilters[0])
    }
}
