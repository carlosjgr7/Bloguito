package com.carlosjgr7.bloguito.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.carlosjgr7.bloguito.data.local.response.PostEntity

@Dao
interface IPostDao {
        @Query("SELECT * FROM PostEntity")
        suspend fun getAllPost():List<PostEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertPost(post: PostEntity)

        @Update
        suspend fun updatePost(post: PostEntity)

        @Delete
        suspend fun deletePost(post: PostEntity)
}