package nguyentrandroid.com.paginglibexample.models.like

data class Like(
    val _shards: Shards,
    val hits: Hits,
    val n_each: List<Int>,
    val timed_out: Boolean,
    val took: Int
)