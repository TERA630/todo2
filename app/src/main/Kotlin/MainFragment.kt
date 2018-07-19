package com.example.yoshi.todo2
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.architecture.ext.sharedViewModel

/*  MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡  */
class MainFragment : Fragment() {
    private lateinit var mAdapter: RecyclerViewAdapter
    private val vModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = recycler_view

        val list = vModel.getItemList()
        mAdapter = RecyclerViewAdapter(mList = list)
        recyclerView.adapter = mAdapter
        initItemDragHelper(adapter = mAdapter, _recyclerView = recyclerView)
        recyclerView.setHasFixedSize(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("test", " On Activity Created $vModel")
        vModel.itemList.observe(this@MainFragment, Observer {
            Log.i("test", "${this.javaClass}@${this.hashCode()} listened the change ")
            mAdapter.setListOfAdapter(vModel.getItemList())
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun initItemDragHelper(adapter: RecyclerViewAdapter, _recyclerView: RecyclerView) {
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
    }
}
