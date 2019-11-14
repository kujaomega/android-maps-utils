package com.google.maps.android.utils.demo.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment


/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}
