package nguyentrandroid.com.paginglibexample.models.like

data class Shards(
    val failed: Int,
    val skipped: Int,
    val successful: Int,
    val total: Int
)