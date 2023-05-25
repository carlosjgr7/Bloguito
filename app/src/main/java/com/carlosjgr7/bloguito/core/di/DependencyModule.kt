package com.carlosjgr7.bloguito.core.di

import android.content.Context
import androidx.room.Room
import com.carlosjgr7.bloguito.data.local.BloguitoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyModule {


    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext appContex: Context): BloguitoDataBase {
        return Room.databaseBuilder(appContex, BloguitoDataBase::class.java, "BloguitoDB").build()
    }


}