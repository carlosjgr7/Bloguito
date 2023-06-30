package com.carlosjgr7.bloguito.home.data.network

import com.carlosjgr7.bloguito.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HomeNetworkDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getLatestPosts(): Flow<List<Post>> = callbackFlow {
        val subscription = firestore.collection("posts")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                } else if (snapshot != null) {
                    val posts = snapshot.documents.mapNotNull { it.toObject(Post::class.java) }
                    trySend(posts.toList())
                }
            }
        awaitClose { subscription.remove() }
    }

}