package com.google.maps.android.utils.demo.maps

import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerItem(
    private val titleEvent: String,
    val id: String,
    private val description: String,
    private val latLng: LatLng,
    val eventImage: String

) : ClusterItem {


    override fun getSnippet(): String {
        return limitString(30, this.description)
    }

    override fun getTitle(): String {
        return limitString(28, this.titleEvent)
    }

    private fun limitString(charLimit: Int, stringToShow: String): String{
        if(stringToShow.length >= charLimit){
            return stringToShow.substring(0, charLimit-3) + "..."
        }
        return stringToShow
    }

    constructor(lat: Double, lng: Double, title: String, snippet: String):this(
        id = "",
        titleEvent = title,
        description = snippet,
        latLng = LatLng(lat,lng),
        eventImage = ""
    )



    override fun getPosition(): LatLng {
        return this.latLng
    }
}