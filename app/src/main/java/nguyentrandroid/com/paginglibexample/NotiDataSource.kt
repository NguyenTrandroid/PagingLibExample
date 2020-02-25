package nguyentrandroid.com.paginglibexample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import nguyentrandroid.com.paginglibexample.api.ApiClient
import nguyentrandroid.com.paginglibexample.api.ApiService
import nguyentrandroid.com.paginglibexample.models.notis.Hit
import nguyentrandroid.com.paginglibexample.utils.NetworkState
import kotlin.coroutines.CoroutineContext


class NotiDataSource(coroutineContext: CoroutineContext) : PageKeyedDataSource<String, Hit>(){

    private val apiService = ApiClient.getClient()
        .create(ApiService::class.java)
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>() //we need to observe these


    var u: String = "5bd2ec89a7262a092eb062f7"
    var l: Int = 10
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Hit>
    ) {
        initialLoad.postValue(NetworkState.LOADING)
        scope.launch {
            try {
                val response = apiService.notis(u, l)
                when {
                    response.isSuccessful -> {
                        response.body().let {
                            l = it?.hits?.total!!
                            val key: Int = it?.hits?.hits?.size!! - 1
                            Log.d("Size",""+it.hits.hits.size)
                            callback.onResult(
                                it?.hits?.hits ?: emptyList(),
                                null,
                                makeSort(it?.hits?.hits[key].sort)

                            )
                            initialLoad.postValue(NetworkState.LOADED)

                        }
                    }
                }

            } catch (exception: Exception) {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error("Network error")
                initialLoad.postValue(error)
                Log.e("LikeDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
        networkState.postValue(NetworkState.LOADING)
        scope.launch {
            try {
                val response = apiService.notisAfter(u, l, params.key)

                when {
                    response.isSuccessful -> {
                        retry = null
                        response.body().let {
                            val key: Int = it?.hits?.hits?.size!! - 1

                            callback.onResult(
                                it?.hits?.hits ?: emptyList(),
                                makeSort(it?.hits?.hits)
                            )
                            networkState.postValue(NetworkState.LOADED)
                            Log.d("AAA", makeSort(it?.hits?.hits[key].sort))

                        }

                    }
                }

            } catch (exception: Exception) {
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkState.error("Network Error"))
                Log.e("LikeDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun makeSort(objects: List<Any>?): String {
        return try {
            objects?.run {
                val stringBuilder = StringBuilder("[")
                for (i in objects.indices) {
                    when (val o = objects[i]) {
                        is Long -> {
                            stringBuilder.append((o as Long).toLong())
                        }
                        is Double -> {
                            stringBuilder.append((o as Double).toDouble())
                        }
                        is String -> {
                            stringBuilder.append("\"")
                            stringBuilder.append(o)
                            stringBuilder.append("\"")
                        }
                    }
                    if (i < objects.size - 1) {
                        stringBuilder.append(",")
                    }
                }
                stringBuilder.append("]")
                stringBuilder.toString()
            } ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }
}