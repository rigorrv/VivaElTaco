package com.github.vivaeltaco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivaeltaco.R
import kotlinx.android.synthetic.main.menu_logo_fragment.*
import kotlinx.android.synthetic.main.menu_logo_fragment.view.*

class LogoFragment : Fragment() {

    var toggle = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.menu_logo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuBtn.setOnClickListener {
            toggle = !toggle
            if (toggle)
                menuBox.visibility = View.VISIBLE
            else
                menuBox.visibility = View.GONE
        }
        menuFoodBtn.setOnClickListener{
            menuBox.visibility = View.GONE
            toggle = !toggle
            communicator.getFood("")

        }
        locationsBtn.setOnClickListener{
            menuBox.visibility = View.GONE
            toggle = !toggle
            communicator.getAddress("")

        }
    }
}