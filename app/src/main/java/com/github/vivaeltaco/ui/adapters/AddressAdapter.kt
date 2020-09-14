package com.github.vivaeltaco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vivaeltaco.R
import com.github.vivaeltaco.model.Address
import com.github.vivaeltaco.ui.fragments.communicator
import kotlinx.android.synthetic.main.recycler_address_view.view.*

class AddressAdapter : RecyclerView.Adapter <AddressAdapter.ViewHolder>() {
    var addressList : List<Address.AddressItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_address_view, parent, false)
        return ViewHolder(view)
    }

    fun setQueries(queryList: List<Address.AddressItem>){
        this.addressList = queryList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(addressList[position])
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){

        val addressText = itemView.addressText
        val stateText = itemView.stateText
        fun onBind(addresItems : Address.AddressItem){
            addressText.text = addresItems.address
            stateText.text = addresItems.state

            itemView.setOnClickListener {

                communicator.gotoLocation(addresItems.latitude.toDouble(), addresItems.longitude.toDouble())
            }
        }
    }

}