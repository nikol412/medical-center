package com.nikol412.medicalcenter.db.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Diagnosis : RealmObject {

    @PrimaryKey
    var id: Int = 0

    var diseaseName: String? = null
    var drugsId: RealmList<Drug>? = null

    var resultInspection: String? = null
    var resultAnalysis: String? = null

    constructor() : super()

    constructor(
        id: Int,
        diseaseName: String,
        drugsId: List<Drug>,
        inspection: String,
        analysis: String
    ) {
        this.id = id
        this.diseaseName = diseaseName
        val realmList = RealmList<Drug>()
        realmList.addAll(drugsId)
        this.drugsId = realmList
        this.resultInspection = inspection
        this.resultAnalysis = analysis
    }

    companion object {
        const val resultInspectionExample = "Жалоб нет \nДыхание ровное \nТемпература: 36.4"
        const val resultAnalysisExample = "Группа крови: О (I) \nЭритроциты: 11 \nЛейкоциты: 5"

        val diagnosisList = listOf(
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
    }
}