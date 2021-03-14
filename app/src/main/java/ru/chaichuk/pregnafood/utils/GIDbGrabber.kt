package ru.chaichuk.pregnafood.utils

import android.util.Log.d
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.jsoup.Jsoup
import ru.chaichuk.pregnafood.data.Food


class GIDbGrabber {

    fun grab() {
        val myDb = Firebase.database("https://pregnafood-default-rtdb.europe-west1.firebasedatabase.app/").reference

        for (id in 1..2746) {
            val url = "http://glycemicindex.com/foodSearch.php?num=${id}&ak=detail"
            var food = Food(id = id)
            try {
                val doc = Jsoup.connect(url).get()
                doc.select("table#table43")
                    .first()
                    .select("tr")[3]
                    .select("table tr")
                    .forEach { row ->
                        val name = row.select("td").first()
                        val value = row.select("td").next()
                        if (name != null && value != null) {
                            if (name.text().isNotEmpty() && value.text().isNotEmpty()) {
                                //d("PregnaFood", "${name.text()} -> ${value.text()}")
                                when (name.text()) {
                                    "Food Name" -> food.name = value.text()
                                    "Food Manufacturer" -> food.manufacturer = value.text()
                                    "GI (vs Glucose)" -> food.gi = value.text().toInt()
                                    "Standard Serve Size (g)" -> food.serveSize =
                                        value.text().toInt()
                                    "Carbohydrate per Serve (g)" -> food.carbohydratePerServe =
                                        value.text().toInt()
                                    "Glycemic Load (GL)" -> food.gl = value.text().toInt()
                                    "Country" -> food.country = value.text()
                                    "Product Category" -> food.category = value.text()
                                    "Year of Test" -> food.yearOfTest = value.text().toInt()
                                    "SEM" -> food.sem = value.text().toInt()
                                    "Time Period of Test" -> food.timePeriodOfTest = value.text()
                                    "Number of Subjects in Test" -> food.numberOfSubjectsInTest =
                                        value.text()
                                    "Type of Subjects in Test" -> food.typeOfSubjectsInTest =
                                        value.text()
                                    "Reference / Source of Data" -> food.sourceOfData = value.text()
                                }
                            }
                        }
                    }
                d("PregnaFood", "${id} -> ${food.toString()}")
                myDb.child(food.id.toString()).setValue(food)
            } catch (e: Exception) {
                d("PregnaFood", e.toString())
            }
        }
    }

}