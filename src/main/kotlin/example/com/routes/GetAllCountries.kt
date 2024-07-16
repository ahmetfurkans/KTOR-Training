package example.com.routes

import example.com.models.ApiResponse
import example.com.models.ApiResponse.Companion.calculatePage
import example.com.models.countryMap
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllCountries() {
    get("/countries") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..4)

            val apiResponse = ApiResponse(
                success = true,
                message = "Ok",
                prevPage = calculatePage(page)["prevPage"],
                nextPage = calculatePage(page)["nextPage"],
                countries = countryMap[page-1]!!
            )

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Invalid page size"),
                status = HttpStatusCode.NotFound
            )
        }
    }
}
