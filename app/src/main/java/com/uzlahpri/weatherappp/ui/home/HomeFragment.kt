package com.uzlahpri.weatherappp.ui.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uzlahpri.weatherappp.databinding.FragmentHomeBinding
import com.uzlahpri.weatherappp.helper.Constant
import com.uzlahpri.weatherappp.helper.DateCorverter
import com.uzlahpri.weatherappp.helper.TimeConverter
import im.delight.android.location.SimpleLocation

class HomeFragment : Fragment() {
    private val REQUEST_CODE = 1

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    var location: SimpleLocation? = null
    var latitude: String? = null
    var longitude: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        location = SimpleLocation(context)
        if (!location!!.hasLocationEnabled()) {
            SimpleLocation.openSettings(context)
        } else {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                activity?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                }
            } else {
                location = SimpleLocation(context)
                latitude = String.format("%.6f", location?.latitude)
                longitude = String.format("%.6f", location?.longitude)
                Log.e("Latitude", "" + latitude)
                Log.e("longitude", "" + longitude)
            }
        }
        homeViewModel.getWeatherDataWIthGPS(latitude!!, longitude!!, Constant.METRIC)
        homeViewModel.locationData.observe(viewLifecycleOwner, Observer { locationGPS ->
            locationGPS?.let {
                homeBinding.tvHomeCityName.text = locationGPS.name
                homeBinding.tvHomeDate.text = DateCorverter()
                homeBinding.tvStateTemperatur.text = locationGPS.main!!.temp!!.toString()
                //manggil data yg berbentuk list
                homeBinding.tvCondition.text = locationGPS.weather!!.get(0)!!.description

                homeBinding.tvPressureHome.text =
                    TimeConverter((locationGPS.main.pressure)!!.toLong())
                homeBinding.tvWindHome.text = TimeConverter((locationGPS.wind!!.speed)!!.toLong())
                homeBinding.tvSunriseHome.text =
                    TimeConverter((locationGPS.sys!!.sunrise)!!.toLong())

                //buat ngambil gambar dari drawable sesuai kondisi
                homeBinding.ivStatePicture.setImageResource(
                    resources.getIdentifier(
                        "ic_" + locationGPS.weather?.get(0)?.icon,
                        "drawable",
                        view.context.packageName
                    )
                )
            }
            homeViewModel.locationLoading.observe(viewLifecycleOwner, Observer { loading ->
                loading?.let {
                    if (it) {
                        homeBinding.pbLoadingHome.visibility = View.VISIBLE
                    } else {
                        homeBinding.pbLoadingHome.visibility = View.GONE
                    }
                }
            })
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location = SimpleLocation(context)
                latitude = String.format("%.6f", location?.latitude)
                longitude = String.format("%.6f", location?.longitude)
                Log.e("Latitude", "" + latitude)
                Log.e("longitude", "" + longitude)

                homeViewModel.getWeatherDataWIthGPS(latitude!!, longitude!!, Constant.METRIC)
            } else {
                Toast.makeText(context, "Izin Ditolak", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}