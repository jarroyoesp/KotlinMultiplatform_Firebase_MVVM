package com.jarroyo.sharedcode.data.repository

import co.touchlab.firebase.firestore.*
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.createUser.CreateUserRequest
import kotlinx.coroutines.flow.flow

class FirebaseRepository() {

    val COLLECTION_USERS = "Users"
    val DOCUMENT_USER_NAME = "name"

    /***********************************************************************************************
     * GET USERS FROM FIREBASE
     **********************************************************************************************/
    suspend fun getFirebaseUserList(): Response<List<FirebaseUser>> {
        var list = getListFromFirebase()
        return Response.Success(list)
    }

    suspend fun getListFromFirebase(): List<FirebaseUser> {
        val reponse = getFirebaseInstance()
            .collection(COLLECTION_USERS)
            .suspendGet()

        val list = parseFirebaseUserList(reponse.documents_)
        return list
    }


    private fun parseFirebaseUserList(documentSnapshots: List<DocumentSnapshot>): List<FirebaseUser> {
        return documentSnapshots.map {
            parseFirebaseUser(it)
        }
    }

    private fun parseFirebaseUser(documentSnapshot: DocumentSnapshot): FirebaseUser {
        val level = documentSnapshot.data_()
        @Suppress("UNCHECKED_CAST")
        val name = level?.get(DOCUMENT_USER_NAME) as String
        return FirebaseUser(name)
    }

    /***********************************************************************************************
     * GET USERS FROM FIREBASE FLOW
     **********************************************************************************************/
    suspend fun getDiscoverFlow() = flow {
        emit(getFirebaseUserList())
    }

    /***********************************************************************************************
     * CREATE USER
     **********************************************************************************************/
    suspend fun createUser(request: CreateUserRequest) : Response<List<FirebaseUser>> {
        val userMap = mutableMapOf<String, String>()
        userMap.put(DOCUMENT_USER_NAME, request.firebaseUser.name)
        getFirebaseInstance().collection(COLLECTION_USERS).suspendAdd(userMap)

        return getFirebaseUserList()
    }
}