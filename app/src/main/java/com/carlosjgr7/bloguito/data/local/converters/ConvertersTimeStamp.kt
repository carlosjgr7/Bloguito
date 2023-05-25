package com.carlosjgr7.bloguito.data.local.converters

import androidx.room.TypeConverter
import java.sql.Timestamp

class ConvertersTimeStamp {

    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? {
        return value?.let { Timestamp(it) }
    }

    @TypeConverter
    fun toTimestamp(timestamp: Timestamp?): Long? {
        return timestamp?.time
    }
}