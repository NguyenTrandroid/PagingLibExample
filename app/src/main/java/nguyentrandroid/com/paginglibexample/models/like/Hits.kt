package nguyentrandroid.com.paginglibexample.models.like

data class Hits(
    val hits: List<Hit>,
    val max_score: Any,
    val total: Int
)