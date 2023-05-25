package com.carlosjgr7.bloguito.data.local.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carlosjgr7.bloguito.data.model.Post
import java.sql.Timestamp

@Entity
data class PostEntity(
    @PrimaryKey
    val id:String = "" ,
    val profile_picture: String = "",
    val profile_name:String = "",
    val post_timestamp: Timestamp? = null,
    val post_image:String = ""
)

fun List<PostEntity>.toPost():List<Post>{
    val postList:MutableList<Post> = mutableListOf()
    this.forEach {
        postList.add(
            Post(it.id,it.profile_picture,it.profile_name,it.post_timestamp,it.post_image)
        )
    }
    return postList.toList()
}
