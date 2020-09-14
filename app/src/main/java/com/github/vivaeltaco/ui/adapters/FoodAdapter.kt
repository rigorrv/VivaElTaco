package com.github.vivaeltaco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vivaeltaco.R
import com.github.vivaeltaco.model.Food
import com.github.vivaeltaco.ui.fragments.communicator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_food_view.view.*

class FoodAdapter : RecyclerView.Adapter <FoodAdapter.ViewHolder>() {

    var foodList : List<Food.ItemFood> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_food_view, parent, false)
        return ViewHolder(view)
    }

    fun setFood(foodList: List<Food.ItemFood>){
        this.foodList = foodList
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){

        val nameFood = itemView.nameFoodText
        val typeFood = itemView.typeFoodText
        val iconFood = itemView.iconFood
        val priceFood = itemView.priceText
        fun onBind(itemFood : Food.ItemFood){
            nameFood.text = itemFood.name
            typeFood.text = itemFood.type
            priceFood.text = itemFood.price
            Picasso.get()
                .load(itemFood.icon)
                .into(iconFood)

            itemView.setOnClickListener {
                communicator.foodFragment(itemFood.picture, itemFood.name, itemFood.price, itemFood.info)
            }
        }
    }

}