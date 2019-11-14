package com.learnfromashes.eventsapp.ui.map

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.Algorithm
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.utils.demo.R
import com.google.maps.android.utils.demo.binding.FragmentDataBindingComponent
import com.google.maps.android.utils.demo.databinding.FragmentMapsBinding
import com.google.maps.android.utils.demo.maps.MarkerItem
import com.google.maps.android.utils.demo.ui.map.MapsViewModel
import com.google.maps.android.utils.demo.util.autoCleared


class MapsFragment : Fragment(), OnMapReadyCallback{

    var dataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentMapsBinding>()

    private lateinit var mMap: GoogleMap

    lateinit var mapsViewModel: MapsViewModel

    private var clusterManager: ClusterManager<MarkerItem>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        mapsViewModel.readItems(resources)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_maps,container,false, dataBindingComponent)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setLifecycleOwner(viewLifecycleOwner)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_support_fragment)
        mapFragment?.let{
            if(mapFragment is SupportMapFragment){
//                val locationManager =
//                    activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                mapFragment.onCreate(savedInstanceState)
                mapFragment.onResume()
                mapFragment.getMapAsync(this)
            }

        }?.run {

        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val metrics = DisplayMetrics()
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics)
        mapsViewModel.getAlgorithm().updateViewSize(metrics.widthPixels, metrics.heightPixels)

        clusterManager = ClusterManager<MarkerItem>(this.context,mMap)
        clusterManager?.algorithm = mapsViewModel.getAlgorithm() as Algorithm<MarkerItem>

        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)

        clusterManager?.let {clusterM ->
            clusterM.setOnClusterItemClickListener{markerItem ->  clusterItemClickListener(markerItem)
            }
//            clusterM.renderer = EventRenderer(this.context!!, mMap, clusterM)
        }

        val position =((this.clusterManager as ClusterManager).algorithm as NonHierarchicalViewBasedAlgorithm).items.random().position
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 14f))

    }

    private fun clusterItemClickListener(markerItem: MarkerItem?): Boolean{
        println("TEST")
        return false
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
