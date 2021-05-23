package com.nikol412.medicalcenter.db.repository

import com.nikol412.medicalcenter.db.models.Appointment
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class AppointmentRepository {
    private val realm = Realm.getDefaultInstance()

    fun createAppointment(appointment: Appointment) {

        realm.executeTransaction { realm ->
            val maxId = realm.where(Appointment::class.java)
                .max(Appointment::id.name)
                ?.toInt() ?: 0
            appointment.id = maxId + 1
            realm.copyToRealmOrUpdate(appointment)
        }
    }

    fun getAppointmentsFlowable(): Flowable<RealmResults<Appointment>> {
        return realm.where(Appointment::class.java)
            .sort(Appointment::date.name, Sort.ASCENDING, Appointment::time.name, Sort.ASCENDING)
            .findAllAsync()
            .asFlowable()
    }

    fun getAppointmentById(id: Int): Flowable<Appointment> {
        return realm.where(Appointment::class.java)
            .equalTo(Appointment::id.name, id)
            .findFirstAsync()
            .asFlowable()
    }
}