package nguyentrandroid.com.paginglibexample.models.notis

data class Hits(
    val hits: List<Hit>,
    val max_score: Any,
    val total: Int
)