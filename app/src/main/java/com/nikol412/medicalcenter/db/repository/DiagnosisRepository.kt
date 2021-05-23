package com.nikol412.medicalcenter.db.repository

import com.nikol412.medicalcenter.db.models.Diagnosis
import io.realm.Realm

class DiagnosisRepository {
    private val realm = Realm.getDefaultInstance()

    fun makeDiagnosise(diagnosis: Diagnosis): Diagnosis {
        realm.executeTransaction {
            val maxId = realm.where(Diagnosis::class.java)
                .max(Diagnosis::id.name)
                ?.toInt()

            val resultId = maxId ?: 0
            diagnosis.id = resultId + 1
        }

        return diagnosis
    }

    fun getRandomDiagnosis(): Diagnosis {
        return realm.where(Diagnosis::class.java)
            .findAll()
            .random()
    }
}