package com.example.yoshi.todo2
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.architecture.ext.sharedViewModel
/*  MVC/MVP - View
    MainViewModelからListを貰い､フィルターをかけて表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡  */
class MainFragment : Fragment() {
    private val vModel by sharedViewModel<MainViewModel>()

    private lateinit var mAdapter: RecyclerViewAdapter
    private lateinit var filteredList: MutableList<FilteredToDoItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filteredList = vModel.getItemListWithTag("")
        mAdapter = RecyclerViewAdapter(mList = filteredList)
        val recyclerView = recycler_view.apply { setHasFixedSize(true) }
        recyclerView.adapter = mAdapter
        initItemDragHelper(adapter = mAdapter, _recyclerView = recyclerView)
        main_fab.setOnClickListener { fabBtnView: View ->
            val navController = Navigation.findNavController(fabBtnView)
            val bundle = Bundle().apply { putInt("itemNumber", vModel.getItemList().size) }
            navController.navigate(R.id.action_launcher_home_to_detail, bundle)
        }
        performTag.setOnClickListener { v ->
            val string = filterEdit.text.toString()
            val navController = Navigation.findNavController(v)
            val bundle = Bundle().apply { putString("tagString", string) }
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
    private fun initItemDragHelper(adapter: RecyclerViewAdapter, _recyclerView: RecyclerView) {
        val mIth = ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int =
                    makeMovementFlags((ItemTouchHelper.UP + ItemTouchHelper.DOWN), ItemTouchHelper.RIGHT)
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                vModel.swapItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vModel.deleteItem(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                when (actionState) {
                    ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE ->
                        viewHolder?.let { it.itemView.alpha = 0.5f }
                }
            }
            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.alpha = 1.0f
            }
        })
        mIth.attachToRecyclerView(_recyclerView)
    }
}
