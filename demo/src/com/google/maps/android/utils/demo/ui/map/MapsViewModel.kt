package com.google.maps.android.utils.demo.ui.map

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.utils.demo.MyItemReader
import com.google.maps.android.utils.demo.R
import com.google.maps.android.utils.demo.maps.MarkerItem
import com.google.maps.android.utils.demo.model.MyItem
import org.json.JSONException
import java.util.*
import javax.inject.Inject

class MapsViewModel: ViewModel(){

    private val mAlgorithm = NonHierarchicalViewBasedAlgorithm<MarkerItem>(0, 0)

    internal fun getAlgorithm(): NonHierarchicalViewBasedAlgorithm<MarkerItem> {
        return mAlgorithm
    }

    @Throws(JSONException::class)
    internal fun readItems(resources: Resources) {
        val inputStream = resources.openRawResource(R.raw.radar_search)
        val items = MyItemReader().read(inputStream)

        try {
            for (i in 0..99) {
                val offset = i / 60.0
                for (item in items) {
                    val position = item.position
                    val lat = position.latitude + offset
                    val lng = position.longitude + offset
                    val offsetItem = MarkerItem(lat, lng, "", "")
                    mAlgorithm.addItem(offsetItem)
                }
            }
        } finally {
//            mAlgorithm.unlock()
        }
    }
}