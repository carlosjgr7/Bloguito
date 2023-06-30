package com.carlosjgr7.bloguito.core.di

import android.content.Context
import com.carlosjgr7.bloguito.home.data.network.HomeNetworkDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyModule {


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContex: Context) =  appContex

    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun providePostsRepository(firestore: FirebaseFirestore): HomeNetworkDataSource {
        return HomeNetworkDataSource(firestore)
    }
}