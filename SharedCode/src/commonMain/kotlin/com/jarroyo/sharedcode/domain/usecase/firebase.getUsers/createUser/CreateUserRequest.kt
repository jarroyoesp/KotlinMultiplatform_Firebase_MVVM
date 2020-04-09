package com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.createUser

import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class CreateUserRequest(val firebaseUser: FirebaseUser): BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}