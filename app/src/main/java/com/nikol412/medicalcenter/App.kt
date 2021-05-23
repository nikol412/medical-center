package com.nikol412.medicalcenter

import android.app.Application
import com.nikol412.medicalcenter.db.models.Appointment
import com.nikol412.medicalcenter.db.models.Diagnosis
import com.nikol412.medicalcenter.db.models.Doctor
import com.nikol412.medicalcenter.db.models.Drug
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import io.realm.RealmConfiguration
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class App : Application() {
    private val disposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)

        val config = RealmConfiguration
            .Builder()
            .name("Medical Center")
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
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
        val doctors = mutableListOf<Doctor>()
        for ((id, name) in doctorsName.withIndex()) {
            doctors.add(Doctor(id, name, departmentsList.random()))
        }
        val drugs = listOf(
            "А-Церумен",
            "А-пар",
            "АД минус",
            "АД норма",
            "АТ-10",
            "Абактал",
            "Абилифай",
            "Абитаксел",
            "Абомин",
            "Абуцел",
            "Аваксим",
            "Авамис",
            "Авандамет",
            "Авандия",
            "Авастин",
            "Авелокс",
            "Авиа-Море",
            "Авиамарин",
            "Авиоплант",
            "Аводарт",
            "Авонекс",
            "Агалатес",
            "Агапурин",
            "Агрегаль",
            "Агренокс"
        )
        val drugsObjects = mutableListOf<Drug>()
        for ((i, drug) in drugs.withIndex()) {
            drugsObjects.add(
                Drug(
                    i,
                    drugs.random(),
                    Random(System.nanoTime()).nextInt(1, 10),
                    Random(System.nanoTime()).nextInt(5, 20)
                )
            )
        }

        val diagnosis = listOf(
            "Абсцессы прямой кишки",
            "Аденома предстательной железы (доброкачественная гиперплазия предстательной железы)",
            "Аднексит (сальпингит; сальпингоофорит)",
            "Алкоголизм (хронический алкоголизм)",
            "Аллергический дерматит у детей (детский диатез)",
            "Аллергия (лекарственная аллергия)",
            "Альтернативные методы контрацепции (температурный метод; календарный метод; прерванный половой акт)",
            "Амебиаз (амебная дизентерия)",
            "Аминокислоты (белки; незаменимые и заменимые аминокислоты)",
            "Анемия (железодефицитная анемия)",
            "Анкилозирующий спондилит (болезнь Бехтерева)",
            "Антиоксиданты (профилактика повреждающего действия свободных радикалов)",
            "Аритмии (нарушения ритма сердца)",
            "Артериальная гипертензия (гипертоническая болезнь; артериальная гипертония; повышение артериального давления)",
            "Артрит у детей (ювенильный артрит, юношеский спондилоартрит)",
            "Артриты (остеоартрит; ревматоидный артрит)",
            "Атипичные пневмонии (SARS; ТОРС; тяжелый острый респираторный синдром)"
        )
        val diagnosisObjects = mutableListOf<Diagnosis>()
        for ((i, diagnose) in diagnosis.withIndex()) {
            val randomDrugs = mutableListOf<Drug>()
            for (i in 1..Random(System.nanoTime()).nextInt(1, 5)) {
                randomDrugs.add(drugsObjects.random())
            }
            diagnosisObjects.add(
                Diagnosis(
                    i,
                    diagnosis.random(),
                    randomDrugs,
                    Diagnosis.resultInspectionExample,
                    Diagnosis.resultAnalysisExample
                )
            )
        }

        val appointments = mutableListOf<Appointment>()

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        val sdfTime = SimpleDateFormat("hh:mm", Locale.US)

        calendar.add(Calendar.DAY_OF_YEAR, -20)
        for (i in 1..10) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val date = sdf.format(calendar.time)
            val time = sdfTime.format(calendar.time)

            appointments.add(
                Appointment(
                    i,
                    doctors.random(),
                    date,
                    time,
                    diagnosisObjects.random()
                )
            )
        }


        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(doctors)
            realm.copyToRealmOrUpdate(appointments)
        }
    }


}