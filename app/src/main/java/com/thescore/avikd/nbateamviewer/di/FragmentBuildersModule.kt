package com.thescore.avikd.nbateamviewer.di


import com.thescore.avikd.nbateamviewer.nbateamsapi.ui.DetailsViewFragment
import com.thescore.avikd.nbateamviewer.nbateamsapi.ui.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeListViewFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsViewFragment
}
