package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Doctor: RealmObject {
    @PrimaryKey
    var id: Int = 0

    var name: String? = null
    var speciality: String? = null


    constructor(): super()

    constructor(id: Int, name: String, speciality: String) {
        this.id = id
        this.name = name
        this.speciality = speciality
    }
}