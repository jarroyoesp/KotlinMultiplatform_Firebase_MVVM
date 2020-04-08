package com.jarroyo.sharedcode.di

import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.data.repository.FirebaseRepository
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListFlowUseCase
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListUseCase
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = Kodein {

    bind<CoroutineContext>() with provider { ApplicationDispatcher }

    /**
     * USECASES
     */
    bind<GetFirebaseUserListUseCase>() with singleton { GetFirebaseUserListUseCase(instance()) }
    bind<GetFirebaseUserListFlowUseCase>() with singleton { GetFirebaseUserListFlowUseCase(instance()) }

    /**
     * REPOSITORIES
     */
    bind<FirebaseRepository>() with provider { FirebaseRepository() }

}