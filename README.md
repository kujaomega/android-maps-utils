# Error reproduction on setOnClusterItemClickListener: On cluster Item clickListener error

The following code is to reproduce the errors in:
https://issuetracker.google.com/issues/144150368
https://stackoverflow.com/questions/58768704/setonclusteritemclicklistener-on-cluster-item-clicklistener-error
https://github.com/googlemaps/android-maps-utils/issues/580

The code taken as a base to reproduce the error, is the following: https://github.com/googlemaps/android-maps-utils

Library details:

com.google.android.gms:play-services-maps:17.0.0

Device google play services:
DOOGEE X5 3G: 19.6.29
motorolla MotoG 3: 19.6.29

This error has not been tested on previous version of Google Play Services


To reproduce the problem 100% of times, follow the details:

Use one of the following phones with errors:
With Aquaris U Plus, Android 7.1.1(16 GB, 2GB RAM) never had this error. Error rate: 0%
With motorolla MotoG 3,  Android 6.0.1 (8 GB, 1 GB RAM) . Error rate 100%
DOOGEE X5 3G, Android 6.0 (8 GB, 1 GB RAM) . Error rate 100%


1. Open the code with Android Studio
2. Set up a breakpoint in the line 114 of the com.google.maps.android.utils.demo.ui.map.MapsFragment class. at the "println" of the "clusterItemClickListener" method:

private fun clusterItemClickListener(markerItem: MarkerItem?): Boolean{
        println("TEST")
        return false
    }

3. Run the app in debug mode using the Android physical devices
4. When the app is running. Click to a cluster Item to go to the breakpoint.
5. When you get to the breakpoint, wait 4-5 seconds and the error will be thrown.

