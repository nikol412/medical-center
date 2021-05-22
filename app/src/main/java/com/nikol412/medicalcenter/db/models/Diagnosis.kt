package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Diagnosis : RealmObject {

    @PrimaryKey
    var id: Int = 0

    var doctorId: Doctor? = null
    var diseaseName: String? = null
    var drug: Drug? = null

    constructor() : super()

    constructor(id: Int, doctor: Doctor, diseaseName: String, drug: Drug) {
        this.id = id
        this.doctorId = doctorId
        this.diseaseName = diseaseName
        this.drug = drug
    }
}