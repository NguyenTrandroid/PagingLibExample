package nguyentrandroid.com.paginglibexample.models.notis

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit")
data class Hit(
    @PrimaryKey val _id: String,
    @ColumnInfo val _index: String,
    @ColumnInfo val _score: Any,
    @ColumnInfo val _source: Source,
    @ColumnInfo val _type: String,
    @ColumnInfo val sort: List<Long>
)