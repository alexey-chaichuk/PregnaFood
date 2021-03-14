package ru.chaichuk.pregnafood.data

data class Food (
        var id: Int = 0,
        var name: String = "",
        var manufacturer: String = "",
        var gi: Int = 0,
        var serveSize: Int = 0,
        var carbohydratePerServe: Int = 0,
        var gl: Int = 0,
        var country: String = "",
        var category: String = "",
        var yearOfTest: Int = 0,
        var sem: Int = 0,
        var timePeriodOfTest: String = "",
        var numberOfSubjectsInTest: String = "",
        var typeOfSubjectsInTest: String = "",
        var sourceOfData: String = ""
)