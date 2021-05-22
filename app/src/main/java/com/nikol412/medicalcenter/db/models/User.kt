package com.nikol412.medicalcenter.db.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject {

    @PrimaryKey
    var id: Int = 0

    var name: String? = null
    var dateOfBirth: String? = null
    private var sex: String = Sex.OTHER.name
    var sexEnum: Sex
    set(value) {
        sex = value.name
    }
    get() {
        return try {
            Sex.valueOf(sex)
        } catch (e: IllegalArgumentException) {
            Sex.OTHER
        }
    }
    var passportData: String? = null
    var address: String? = null
    var phoneNumber: String? = null
    var login: String? = null
    var password: String? = null

    constructor() : super()

    constructor(login: String, password: String) {
        this.login = login
        this.password = password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (this.name != other.name) return false
        if (this.dateOfBirth != other.dateOfBirth) return false
        if (this.sex != other.sex) return false
        if (this.passportData != other.passportData) return false
        if (this.address != other.address) return false
        if (this.phoneNumber != other.phoneNumber) return false
        if (this.login != other.login) return false
        if (this.password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 31

        result += 31 * (name?.hashCode() ?: 0)
        result += 31 * (dateOfBirth?.hashCode() ?: 0)
        result += 31 * (sex.hashCode())
        result += 31 * (passportData?.hashCode() ?: 0)
        result += 31 * (address?.hashCode() ?: 0)
        result += 31 * (phoneNumber?.hashCode() ?: 0)
        result += 31 * (login?.hashCode() ?: 0)
        result += 31 * (password?.hashCode() ?: 0)

        return result
    }

}

enum class Sex {
    MALE, FEMALE, OTHER
}