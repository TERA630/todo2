package com.example.yoshi.todo2
import android.os.Bundle
import android.support.v7.widget.RecyclerView
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
class RecyclerViewAdapter(private var mList: List<FilteredToDoItem>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // method
    override fun getItemCount(): Int = mList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        val itemVH = ItemViewHolder(rowView)
        itemVH.binding.itemList = mList.toList()
        return itemVH
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).binding.itemNumber = position
        holder.binding.periodViewer.text = "${mList[position].item.startLine} ～ ${mList[position].item.deadLine} "
        val bundle = Bundle()
        bundle.putInt("itemNumber", position)
        holder.itemView.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_launcher_home_to_detail, bundle))
    }

    fun setListOfAdapter(_list: MutableList<FilteredToDoItem>) {
        this.mList = _list
    }
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ListItemsBinding.bind(itemView)
    }
}