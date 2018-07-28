package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_filtered.*
import org.koin.android.architecture.ext.sharedViewModel

class FilteredFragment : Fragment() {
    private val vModel by sharedViewModel<MainViewModel>()
    private lateinit var mAdapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = recycler_viewOfFilter
        val filteredList = filterItemByTag("home")
        mAdapter = RecyclerViewAdapter(mList = filteredList, mListMap = filterToRawMap)
        recyclerView.adapter = mAdapter

        //       initItemDragHelper(adapter = mAdapter, _recyclerView = recyclerView)
        recyclerView.setHasFixedSize(true)
        //        fab.setOnClickListener { fabBtnView ->
        //           val navController = Navigation.findNavController(fabBtnView)
//            val bundle = Bundle()
//            bundle.putInt("itemNumber", vModel.getItemList().size)
//            navController.navigate(R.id.action_launcher_home_to_detail, bundle)
    }

    fun filterItemByTag(filterString: String): Map<Int, Int> {
        val rawList = vModel.getItemList()
        var filteredIndex = 0
        for (i in 0..rawList.lastIndex) {
            if (rawList[i].tagString contains "home") {
                filteredList[filteredIndex].rawId = i
                filteredList[filteredIndex].item = rawList[i].copy()
            }
            return filteredList.toList()
        }
        return filteredList
    }


/*    private fun initItemDragHelper(adapter: RecyclerViewAdapter, _recyclerView: RecyclerView) {
        val mIth = ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int =
                    makeMovementFlags((ItemTouchHelper.UP + ItemTouchHelper.DOWN), ItemTouchHelper.RIGHT)
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                vModel.swapItem(viewHolder.adapterPosition, target.adapterPosition)
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
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
    } */
}