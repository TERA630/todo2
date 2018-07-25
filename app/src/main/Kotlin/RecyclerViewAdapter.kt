package com.example.yoshi.todo2

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yoshi.todo2.databinding.ListItemsBinding
import kotlinx.android.synthetic.main.list_items.view.*
import org.koin.android.architecture.ext.sharedViewModel

/*
    MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡
 */
class RecyclerViewAdapter(private var mList: List<ToDoItem>,private val vModel:MainViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // method
    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // holder.itemView.itemTile.text = mList[position].title
        (holder as ItemViewHolder).binding.itemNumber = position
        (holder as ItemViewHolder).binding.periodViewer.text = "${mList[position].startLine} ～${mList[position].deadLine} "
        val bundle = Bundle()
        bundle.putInt("itemNumber", position)
        holder.itemView.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_launcher_home_to_detail, bundle))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent,false)
        val itemVH = ItemViewHolder(rowView)
        itemVH.binding.itemList=mList.toList()
    //    Log.i("test", "RowView ${rowView.hashCode()} has ViewHolder ${itemVH.hashCode()} and binding ${itemVH.binding.hashCode()}");
        return itemVH }
    /*
     fun move(_fromPosition: Int, _toPosition: Int) {
         val textStack = lists.removeAt(index = _fromPosition)
         lists.add(_toPosition, textStack)
         this@BindingRecyclerViewAdapter.notifyItemMoved(_fromPosition, _toPosition)
     } */

    fun setListOfAdapter(_list: MutableList<ToDoItem>) {
        this.mList = _list
    }
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ListItemsBinding.bind(itemView)
    }
}