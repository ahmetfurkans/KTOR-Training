package example.com.models

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val countries: List<Country> = emptyList(),
) {
    companion object {
        fun calculatePage(page: Int): Map<String, Int?> {
            var prevPage: Int? = page
            var nextPage: Int? = page
            if (page in 1..4) {
                nextPage = nextPage?.plus(1)
            }
            if (page in 2..5) {
                prevPage = prevPage?.minus(1)
            }
            if (page == 1) {
                prevPage = null
            }
            if (page == 5) {
                nextPage = null
            }
            return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
        }
    }
}

