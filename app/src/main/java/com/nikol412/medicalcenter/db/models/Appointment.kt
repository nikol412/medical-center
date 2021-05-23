package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Appointment : RealmObject {

    @PrimaryKey
    var id: Int = 0
    var doctor: Doctor? = null
    var date: String? = null
    var time: String? = null

    var diagnosis: Diagnosis? = null
    constructor() : super()

    constructor(id: Int, doctor: Doctor, date: String, time: String, diagnosis: Diagnosis) {
        this.id = id
        this.doctor = doctor
        this.date = date
        this.time = time
        this.diagnosis = diagnosis
    }
}