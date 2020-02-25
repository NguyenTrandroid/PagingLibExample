package nguyentrandroid.com.paginglibexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import nguyentrandroid.com.paginglibexample.models.notis.Hit

class MainViewModel : ViewModel() {
    private val pageSize = 5
    var notisLiveData: LiveData<PagedList<Hit>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        notisLiveData = initializedPagedListBuilder(config).build()

    }
    fun getLikes(): LiveData<PagedList<Hit>> = notisLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<String, Hit> {

        val dataSourceFactory = object : DataSource.Factory<String, Hit>() {
            override fun create(): DataSource<String, Hit> {
                return NotiDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<String, Hit>(dataSourceFactory, config)
    }
}