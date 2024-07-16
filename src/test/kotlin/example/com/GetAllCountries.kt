package example.com

import example.com.models.ApiResponse
import example.com.models.ApiResponse.Companion.calculatePage
import example.com.models.countries
import example.com.models.countryMap
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllCountries {

    @ExperimentalSerializationApi
    @Test
    fun `access countries endpoint, query all pages, assert correct information`() =
        testApplication {
            val pages = 1..4

            pages.forEach { page ->

                println("girdim $page")
                val response = client.get("/countries?page=$page")

                val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
                val expected = ApiResponse(
                    success = true,
                    message = "Ok",
                    prevPage = calculatePage(page = page)["prevPage"],
                    nextPage = calculatePage(page = page)["nextPage"],
                    countries = countryMap.get(page-1)!!,
                )
                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }

    @ExperimentalSerializationApi
    @Test
    fun `access countries endpoint, query non existing page number, assert error`() =
        testApplication {
            val response = client.get("/countries?page=6")

            val expected = ApiResponse(
                success = false,
                message = "Invalid page size"
            )

            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

            assertEquals(
                expected = expected,
                actual = actual
            )
        }

    @ExperimentalSerializationApi
    @Test
    fun `access countries endpoint, query invalid page number, assert error`() =
        testApplication {
            val response = client.get("/countries?page=invalid")
            val expected = ApiResponse(
                success = false,
                message = "Only Numbers Allowed."
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
            assertEquals(
                expected = expected,
                actual = actual
            )
        }
}