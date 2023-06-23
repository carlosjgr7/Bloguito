package com.carlosjgr7.bloguito.data.model

import com.google.firebase.Timestamp
import java.util.UUID

data class Post(
    val post_id: String = UUID.randomUUID().toString(),
    val post_profile_picture: String = "",
    val post_profile_name:String = "",
    val post_timestamp:Timestamp? = null,
    val post_description:String ="",
    val post_image:String = ""
)
