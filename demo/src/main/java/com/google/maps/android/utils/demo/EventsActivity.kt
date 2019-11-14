package com.google.maps.android.utils.demo

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.maps.android.utils.demo.databinding.ActivityEventsBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class EventsActivity: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityEventsBinding>(this, R.layout.activity_events)

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}