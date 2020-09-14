package com.github.vivaeltaco.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.vivaeltaco.R
import com.github.vivaeltaco.model.Address
import com.github.vivaeltaco.model.Food
import com.github.vivaeltaco.ui.communicator.Communicator
import com.github.vivaeltaco.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {

    val recyclerFragment = RecyclerFragment()
    val centralFragment = CentralFragment()
    val logoFragment = LogoFragment()
    lateinit var myViewModel: PostListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.centralFragment, centralFragment)
            .commit()
        supportFragmentManager.beginTransaction().replace(R.id.logoFragment, logoFragment)
            .commit()

        getFood("")
    }

    override fun getFood(type : String) {
        supportFragmentManager.beginTransaction().replace(R.id.recyclerFragment, recyclerFragment)
            .commit()
        myViewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        myViewModel.getFood(type)
        val foodObserver = Observer<Food>{
            centralFragment.getImage()
            recyclerFragment.foodList(it)
        }
        myViewModel.getFoodLiveData().observe(this,foodObserver)
        progresBar()
    }

    override fun getAddress(address: String) {
        supportFragmentManager.beginTransaction().replace(R.id.recyclerFragment, recyclerFragment)
            .commit()
        myViewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        myViewModel.getAddress(address)
        val addressObserver = Observer<Address>{
            centralFragment.getMap(it)
            recyclerFragment.addressList(it)
        }
        myViewModel.getAddressLiveData().observe(this,addressObserver)
        progresBar()
    }

    private fun progresBar() {
        myViewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        var progresBarrObserver = Observer<Boolean>{
            if (true) {
                progressBarMapImg.visibility = View.GONE
                progressBarInfo.visibility = View.GONE
            }
            else{
            progressBarMapImg.visibility = View.VISIBLE
            progressBarInfo.visibility = View.VISIBLE
        }
        }
        myViewModel.loadingLiveData.observe(this, progresBarrObserver)
    }

    override fun foodFragment(picture: String, nameFood: String, price: String, infoFood: String) {
        centralFragment.changePicture(picture, nameFood, price, infoFood)
    }

    override fun gotoLocation(lat: Double, long: Double) {
        centralFragment.gotoLocations(lat, long)
    }
}