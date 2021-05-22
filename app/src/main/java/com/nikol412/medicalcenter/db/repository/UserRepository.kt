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
}