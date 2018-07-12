package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }
}
