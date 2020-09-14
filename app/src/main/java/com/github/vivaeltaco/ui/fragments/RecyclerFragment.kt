package com.github.vivaeltaco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vivaeltaco.R
import com.github.vivaeltaco.model.Address
import com.github.vivaeltaco.model.Food
import com.github.vivaeltaco.ui.adapters.AddressAdapter
import com.github.vivaeltaco.ui.adapters.FoodAdapter
import com.github.vivaeltaco.ui.communicator.Communicator
import kotlinx.android.synthetic.main.recyclerview_fragment.*
import kotlinx.android.synthetic.main.recyclerview_fragment.view.*

lateinit var communicator: Communicator
class RecyclerFragment  : Fragment() {

    var addressAdapter = AddressAdapter()
    var foodAdapter = FoodAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = activity as Communicator
        return inflater.inflate(R.layout.recyclerview_fragment, container, false)
    }


    fun foodList(foodList : Food){
        recyclerView.adapter = foodAdapter
        foodAdapter.setFood(foodList)
    }
    fun addressList(addres : Address){
        recyclerView.adapter = addressAdapter
        addressAdapter.setQueries(addres)
    }
}