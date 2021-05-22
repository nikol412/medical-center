package com.nikol412.medicalcenter

import android.app.Application
import com.nikol412.medicalcenter.db.models.Doctor
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    private val disposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)

        val config = RealmConfiguration
            .Builder()
            .name("Medical Center")
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)

        checkDbIsCreated()
    }

    private fun checkDbIsCreated() {
        val realm = Realm.getDefaultInstance()

        disposable.add(
            realm.where(Doctor::class.java)
                .findAllAsync()
                .asFlowable()
                .subscribe {
                    if (it.isNullOrEmpty()) {
                        createTemplateDB()
                    }
                    realm.close()
                    disposable.clear()
                }
        )
    }

    private fun createTemplateDB() {
        val realm = Realm.getDefaultInstance()
        val doctorsName = listOf(
            "Ардаков Игорь Герасимович",
            "Донченко Иван Андреевич",
            "Кулагина Юлия Анатольевна",
            "Бирюков Евгений Евгеньевич",
            "Васильев Валерий Валентинович",
            "Дылдин Алексей Валерьевич",
            "Девин Игорь Владимирович",
            "Угаров Виктор Михайлович",
            "Демчук Алексей Павлович",
            "Зюлькин Григорий Александрович",
            "Гришина Ольга Константиновна",
            "Карсева Полина Алексеевна",
            "Логинов Сергей Николаевич",
            "Жаркова Алла Юрьевна",
            "Шепелев Алексей Владимирович"
        )
        val departmentsList = listOf(
            "Кардиология",
            "Иммунология",
            "Неврология",
            "Нейрохирургия",
            "Терапия",
            "Травматология",
            "Урология",
            "Эндокринология",
            "Ортезия"
        )

        val doctors = mutableListOf<Doctor>()

        for ((id, name) in doctorsName.withIndex()) {
            doctors.add(Doctor(id, name, departmentsList.random()))
        }
        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(doctors)
        }
    }


}