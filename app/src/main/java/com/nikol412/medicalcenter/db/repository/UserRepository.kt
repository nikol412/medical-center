package com.nikol412.medicalcenter.db.repository

import com.nikol412.medicalcenter.db.models.User
import io.realm.Realm

class UserRepository {
    private val realm = Realm.getDefaultInstance()

    fun createUser(user: User) {
        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(user)
        }
    }

    fun checkUserIsValid(user: User): Boolean {
        val localUser = realm.where(User::class.java)
            .findFirst()

        return localUser?.login == user.login && localUser?.password == user.password
    }

    fun isUserExist(): Boolean {
        return realm.where(User::class.java).findFirst() != null
    }
}