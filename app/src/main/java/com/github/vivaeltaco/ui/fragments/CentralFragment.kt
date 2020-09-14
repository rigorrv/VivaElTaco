package com.github.vivaeltaco.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.github.vivaeltaco.R
import com.github.vivaeltaco.model.Address
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.central_fragment.*


class CentralFragment : Fragment(), OnMapReadyCallback {


    lateinit var mMap: GoogleMap


    var toggle = false
    val TAG: String = "TAG"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.central_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btnMenuTypeFood.setOnClickListener {
            toggle = !toggle
            if (toggle)
                menuFoodTypeBox.visibility = View.VISIBLE
            else
                menuFoodTypeBox.visibility = View.GONE
        }

        searchBtn.setOnClickListener {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            communicator.getAddress(searchText.text.toString())
            searchText.setText("")
        }

        radioButtonBox.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton0 -> {
                    Log.d(TAG, "onCreateView: Favorites")
                    communicator.getFood("")

                }
                R.id.radioButton1 -> {
                    Log.d(TAG, "onCreateView: Entry")
                    communicator.getFood("entry")

                }
                R.id.radioButton2 -> {
                    communicator.getFood("breakfast")

                }
                R.id.radioButton3 -> {
                    communicator.getFood("lunch")

                }
                R.id.radioButton4 -> {
                    communicator.getFood("dessert")

                }
                R.id.radioButton5 -> {
                    communicator.getFood("juice")

                }
                R.id.radioButton6 -> {
                    communicator.getFood("alcohol")

                }
                R.id.radioButton7 -> {
                    communicator.getFood("coffe")

                }
            }
            menuFoodTypeBox.visibility = View.GONE
            toggle = !toggle
        }


        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.google) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    fun changePicture(image: String, nameFood : String , price : String, infoFood : String) {

        foodNamePicture.text = nameFood
        priceFoodPicture.text = price
        infoFoodText.text = infoFood

        Picasso.get()
            .load(image)
            .into(imageFood)
    }

    fun getMap(items: Address) {
        imageBox.visibility = View.GONE
        googleMapBox.visibility = View.VISIBLE
        mMap.clear()
        for (i in items) {
            val newMarkers = LatLng(i.latitude.toDouble(), i.longitude.toDouble())
            mMap.addMarker(MarkerOptions().position(newMarkers).title("USA"))
        }
        btnMenuTypeFood.visibility = View.GONE
        searchBox.visibility = View.VISIBLE

    }

    fun getImage() {
        imageBox.visibility = View.VISIBLE
        googleMapBox.visibility = View.GONE
        btnMenuTypeFood.visibility = View.VISIBLE
        searchBox.visibility = View.GONE
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap // Lately initialize here...
        mMap.uiSettings.isZoomControlsEnabled = true
        val usa = LatLng(38.749831, -102.096594)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(usa, 3f))
    }

    fun gotoLocations(lat: Double, long: Double) {
        val usa = LatLng(lat, long)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 8f))
    }



}