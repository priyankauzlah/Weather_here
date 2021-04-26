package com.uzlahpri.weatherappp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.uzlahpri.weatherappp.R
import com.uzlahpri.weatherappp.databinding.FragmentLocationBinding
import im.delight.android.location.SimpleLocation

class LocationFragment : Fragment() {

    private val REQUEST_CODE = 1
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var locationDataBinding: FragmentLocationBinding

    var location: SimpleLocation? = null
    var latitude: String? = null
    var longitude: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false)
        return locationDataBinding.root
    }
}