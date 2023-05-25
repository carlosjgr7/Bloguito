package com.carlosjgr7.bloguito.data.model

import java.sql.Timestamp
import java.util.UUID

data class Post(
    val id: String = UUID.randomUUID().toString(),
    val profile_picture: String = "",
    val profile_name:String = "",
    val post_timestamp:Timestamp? = null,
    val post_image:String = ""
)
