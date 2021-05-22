package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Doctor: RealmObject {
    @PrimaryKey
    var id: Int = 0

    var name: String? = null
    var speciality: String? = null


    constructor(): super()
}