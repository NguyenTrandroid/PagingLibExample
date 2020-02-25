package nguyentrandroid.com.paginglibexample.models.notis

data class Notis(
    val _shards: Shards,
    val hits: Hits,
    val timed_out: Boolean,
    val took: Int
)