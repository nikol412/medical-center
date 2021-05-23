package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Drug : RealmObject {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Drug) return false

        if (this.id != other.id) return false
        if (this.name != other.name) return false
        if (this.numberOfDays != other.numberOfDays) return false
        if (this.numberOfDoses != other.numberOfDoses) return false

        return true

    }

    override fun hashCode(): Int {
        var result = 31

        result += 31 * (name?.hashCode() ?: 0)
        result += 31 * (id.hashCode())
        result += 31 * (numberOfDoses?.hashCode() ?: 0)
        result += 31 * (numberOfDays?.hashCode() ?: 0)

        return result
    }
}