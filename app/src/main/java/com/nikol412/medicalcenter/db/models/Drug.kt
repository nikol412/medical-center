package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Drug : RealmObject {
    @PrimaryKey
    var id: Int = 0

    var name: String? = null

    var numberOfDays: Int? = null
    var numberOfDoses: Int? = null

    constructor() : super()

    constructor(id: Int, name: String, numberOfDays: Int, numberOfDoses: Int) {
        this.id = id
        this.name = name
        this.numberOfDays = numberOfDays
        this.numberOfDoses = numberOfDoses
    }
}