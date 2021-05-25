package com.nikol412.medicalcenter.extensions

//inline fun <T: Any> safeLet(vararg elements: T?, closure: () -> Nothing): List<T> {
//    return if (elements.all { it != null }) {
//        elements.filterNotNull()
//    } else {
//        closure()
//    }
//}

inline fun <T: Any> safeLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}