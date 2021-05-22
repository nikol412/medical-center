package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Appointment : RealmObject {

    @PrimaryKey
    var id: Int = 0
    var doctor: Doctor? = null
    var dateTime: String? = null

    constructor() : super()

    constructor(id: Int, doctor: Doctor, dateTime: String) {
        this.id = id
        this.doctor = doctor
        this.dateTime = dateTime
    }
}