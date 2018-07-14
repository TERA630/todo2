package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("test","Fragment onCreated")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test","Fragment onCreated")
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
        val recyclerViewAdapter = RecyclerViewAdapter(list)
        recyclerView.adapter  = recyclerViewAdapter
        recyclerView.setHasFixedSize(true)

        //TODO STEP 5 - Set an OnClickListener, using Navigation.createNavigateOnClickListener()
//        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.flow_step_one, null)
//        )
        //TODO ENDSTEP 5

        //TODO STEP 6 - Set NavOptions

//        val options = NavOptions.Builder()
//            .setEnterAnim(R.anim.slide_in_right)
//            .setExitAnim(R.anim.slide_out_left)
//            .setPopEnterAnim(R.anim.slide_in_left)
//            .setPopExitAnim(R.anim.slide_out_right)
//            .build()
//
//        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener {
//            findNavController(it).navigate(R.id.flow_step_one, null, options)
//        }
        //TODO ENDSTEP 6

        //TODO STEP 7 - Update the OnClickListener to navigate using an action
//        view.findViewById<Button>(R.id.navigate_action_bt)?.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        )

        //TODO ENDSTEP 7
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
