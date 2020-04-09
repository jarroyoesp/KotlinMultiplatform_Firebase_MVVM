package com.jarroyo.sharedcode.viewModel.firebase

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser


sealed class CreateFirebaseUserState {
    abstract val response: Response<List<FirebaseUser>>?
}
data class SuccessCreateFirebaseUserState(override val response: Response<List<FirebaseUser>>) : CreateFirebaseUserState()
data class LoadingCreateFirebaseUserState(override val response: Response<List<FirebaseUser>>? = null) : CreateFirebaseUserState()
data class ErrorCreateFirebaseUserState(override val response: Response<List<FirebaseUser>>) : CreateFirebaseUserState()