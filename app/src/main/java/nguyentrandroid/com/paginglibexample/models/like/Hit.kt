package nguyentrandroid.com.paginglibexample.models.like

data class Hit(
    val _id: String,
    val _index: String,
    val _score: Any,
    val _source: Source,
    val _type: String,
    val sort: List<Double>
)