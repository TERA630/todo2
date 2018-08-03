package com.example.yoshi.todo2
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yoshi.todo2.databinding.ListItemsBinding
import kotlinx.android.synthetic.main.list_items.view.*

/*
    MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡
 */
class RecyclerViewAdapter(var mList: MutableList<FilteredToDoItem>, val vModel: MainViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // method
    override fun getItemCount(): Int = mList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        val itemVH = ItemViewHolder(rowView)
        itemVH.binding.itemList = mList.toList()
        itemVH.binding.itemTitle.setOnCheckedChangeListener { _, _ -> Log.i("test", "changed was listened") }
        return itemVH
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).binding.itemNumber = position
        holder.binding.periodViewer.text = "${mList[position].unFilter}の${mList[position].item.startLine} ～ ${mList[position].item.deadLine} "
        val bundle = Bundle()
        bundle.putInt("itemNumber", position)
        holder.itemView.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_launcher_home_to_detail, bundle))
    }

    fun setListOfAdapter(_list: MutableList<FilteredToDoItem>) {
        this.mList = _list
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListItemsBinding.bind(itemView)
    }

    fun initItemDragHelper(adapter: RecyclerViewAdapter, _recyclerView: RecyclerView) {
        val mIth = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                return makeMovementFlags((ItemTouchHelper.UP + ItemTouchHelper.DOWN), ItemTouchHelper.RIGHT)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                vModel.swapItem(mList[viewHolder.adapterPosition].unFilter, mList[target.adapterPosition].unFilter)
                // mList.swap(viewHolder.adapterPosition, target.adapterPosition)
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vModel.deleteItem(mList[viewHolder.adapterPosition].unFilter)
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