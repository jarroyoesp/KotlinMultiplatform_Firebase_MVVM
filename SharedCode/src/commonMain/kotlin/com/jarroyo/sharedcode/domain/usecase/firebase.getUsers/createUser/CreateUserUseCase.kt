package com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.createUser

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.FirebaseRepository
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase

open class CreateUserUseCase(val repository: FirebaseRepository) : BaseUseCase<CreateUserRequest, List<FirebaseUser>>() {

    override suspend fun run(): Response<List<FirebaseUser>> {
        return repository.createUser(request!!)
    }
}