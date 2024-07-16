package example.com.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val currencies: String,
    val capital: String,
    val population: Int,
)

val countries = listOf(
    Country("United States", "USD", "Washington, D.C.", 331002651),
    Country("Canada", "CAD", "Ottawa", 37742154),
    Country("United Kingdom", "GBP", "London", 67886011),
    Country("Germany", "EUR", "Berlin", 83783942),
    Country("France", "EUR", "Paris", 65273511),
    Country("Japan", "JPY", "Tokyo", 126476461),
    Country("Australia", "AUD", "Canberra", 25499884),
    Country("Brazil", "BRL", "Brasília", 212559417),
    Country("India", "INR", "New Delhi", 1380004385),
    Country("Russia", "RUB", "Moscow", 145934462),
    Country("China", "CNY", "Beijing", 1439323776),
    Country("South Africa", "ZAR", "Pretoria", 59308690),
    Country("Mexico", "MXN", "Mexico City", 128932753),
    Country("Italy", "EUR", "Rome", 60461826),
    Country("South Korea", "KRW", "Seoul", 51269185),
    Country("Spain", "EUR", "Madrid", 46754778),
    Country("Argentina", "ARS", "Buenos Aires", 45195774),
    Country("Nigeria", "NGN", "Abuja", 206139589),
    Country("Turkey", "TRY", "Ankara", 84339067),
    Country("Indonesia", "IDR", "Jakarta", 273523615)
)

// Group the list of Country objects into sublists of 5 countries each to simulate page
val countryMap: Map<Int, List<Country>> = countries.withIndex()
    .groupBy { it.index / 5 }
    .mapValues { it.value.map { indexedValue -> indexedValue.value } }