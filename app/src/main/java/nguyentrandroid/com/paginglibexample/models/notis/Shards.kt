package nguyentrandroid.com.paginglibexample.models.notis

data class Shards(
    val failed: Int,
    val skipped: Int,
    val successful: Int,
    val total: Int
)