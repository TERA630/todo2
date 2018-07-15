package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject


/*
    MVC/MVP - View
    Controller or Presenter から　Listを貰い､表示する｡
    データ変更を伴うユーザーの入力イベントは　Controller/Presenterに委譲する方針で｡
 */
class MainFragment : Fragment() {
    private val viewModel by inject<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("test","Fragment onCreated")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test", "Fragment onCreated")
        val recyclerView = recycler_view
        val list = listOf("Wakeup",
                "move the core of body",
                "remember what to do",
                "get up",
                "wash the face",
                "change the cloth",
                "make protein shake",
                "eat and drink",
                "go out",
                "run if I can")
        viewModel.initNavAnim()

        val recyclerViewAdapter = RecyclerViewAdapter(list, viewModel)
        recyclerView.adapter  = recyclerViewAdapter
        recyclerView.setHasFixedSize(true)
    }
    /*
    // Swipe / drag listener
    val mIth = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int =
                makeMovementFlags(ItemTouchHelper.UP, ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            listAdapter.move(viewHolder.adapterPosition, target.adapterPosition)
            Globals.Instance.mTodoItems.swap(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Globals.Instance.mTodoItems.removeAt(viewHolder.adapterPosition)
            listAdapter.removeListItem(viewHolder.adapterPosition)
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
    mIth.attachToRecyclerView(recyclerView)
*/

}
