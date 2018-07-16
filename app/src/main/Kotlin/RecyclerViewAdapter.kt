package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.list_items.view.*

/*
    MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡
 */

class RecyclerViewAdapter(private var mList: List<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // method
    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.itemTile.text = mList[position]

        val bundle = Bundle()
        bundle.putInt("itemNumber", position)
        holder.itemView.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_launcher_home_to_detail, bundle))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent,false)
        return RecyclerViewHolder(rowView)
    }
    /*
     fun move(_fromPosition: Int, _toPosition: Int) {
         val textStack = lists.removeAt(index = _fromPosition)
         lists.add(_toPosition, textStack)
         this@BindingRecyclerViewAdapter.notifyItemMoved(_fromPosition, _toPosition)
     } */

    fun setListOfAdapter(_list: MutableList<String>) {
        this.mList = _list
    }

    class RecyclerViewHolder(val itemView:View) : RecyclerView.ViewHolder(itemView)


}