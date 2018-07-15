package com.example.yoshi.todo2

import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.list_items.view.*
import org.koin.android.ext.android.inject

/*
    MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡
 */

class RecyclerViewAdapter(private val mList: List<String>, private val viewModel: MainViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // method
    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.itemTile.text = mList[position]
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent,false)
        rowView.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_launcher_home_to_detail))
        return RecyclerViewHolder(rowView)
    }
    /*
     fun move(_fromPosition: Int, _toPosition: Int) {
         val textStack = lists.removeAt(index = _fromPosition)
         lists.add(_toPosition, textStack)
         this@BindingRecyclerViewAdapter.notifyItemMoved(_fromPosition, _toPosition)
     } */
    class RecyclerViewHolder(val itemView:View) : RecyclerView.ViewHolder(itemView)

}