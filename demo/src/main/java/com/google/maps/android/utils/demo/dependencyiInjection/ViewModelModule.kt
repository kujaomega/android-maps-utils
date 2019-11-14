package com.google.maps.android.utils.demo.dependencyiInjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.maps.android.utils.demo.ui.map.MapsViewModel
import com.google.maps.android.utils.demo.viewmodels.EventsViewModuleFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapsViewModel::class)
    abstract fun bindMapsViewModel(mapsViewModel: MapsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: EventsViewModuleFactory): ViewModelProvider.Factory
}