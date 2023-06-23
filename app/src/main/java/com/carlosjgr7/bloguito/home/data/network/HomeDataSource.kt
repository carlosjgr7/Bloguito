package com.carlosjgr7.bloguito.home.data.network

import android.util.Log
import com.carlosjgr7.bloguito.data.model.Post
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeDataSource @Inject constructor(private val firestore: FirebaseFirestore) {

    //sin actualizacion en tiempo real
//    suspend fun getLatestPosts() = try {
//        firestore.collection("posts")
//            .get()
//            .await()
//            .toObjects(Post::class.java)
//            .asFlow()
//    } catch (e: Exception) {
//        throw e
//    }

    //con actualizacion en tiempo real
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