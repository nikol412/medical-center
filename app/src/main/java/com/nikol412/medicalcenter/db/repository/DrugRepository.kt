package com.nikol412.medicalcenter.db.repository

import com.nikol412.medicalcenter.db.models.Diagnosis
import com.nikol412.medicalcenter.db.models.Drug
import io.realm.Realm

class DrugRepository {
    private val realm = Realm.getDefaultInstance()

    fun makeDrug(drug: Drug): Drug {
        realm.executeTransaction {
            val maxId = realm.where(Drug::class.java)
                .max(Drug::id.name)
                ?.toInt()

            val resultId = maxId ?: 0
            drug.id = resultId + 1
        }

        return drug
    }

    fun getRandomDrug(): Drug {
        return realm.where(Drug::class.java)
            .findAll().random()
    }
}