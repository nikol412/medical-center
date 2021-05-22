package com.nikol412.medicalcenter.db.repository

import com.nikol412.medicalcenter.db.models.Doctor
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmResults

class DoctorsRepository {
    private val realm = Realm.getDefaultInstance()

    fun getDoctors(speciality: String? = null): Flowable<RealmResults<Doctor>> {
        var realmQuery = realm.where(Doctor::class.java)


        speciality?.let {
            realmQuery.equalTo(Doctor::speciality.name, speciality)
        }

        return realmQuery
            .findAllAsync()
            .asFlowable()
    }

    fun createDoctor(doctor: Doctor) {
        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(doctor)
        }
    }
}