package com.carlosjgr7.bloguito.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carlosjgr7.bloguito.data.local.converters.ConvertersTimeStamp
import com.carlosjgr7.bloguito.data.local.response.PostEntity

@Database(entities = [PostEntity::class], version = 1)
@TypeConverters(ConvertersTimeStamp::class)
abstract class BloguitoDataBase : RoomDatabase() {
    abstract fun PostDao(): IPostDao
}