package nguyentrandroid.com.paginglibexample.utils

enum class State {
    DONE, LOADING, ERROR
}

data class NetworkState private constructor(
    val status: State,
    val msg: String? = null
) {
    companion object {
        val LOADED = NetworkState(
            State.DONE
        )
        val LOADING = NetworkState(
            State.LOADING
        )
        fun error(msg: String?) =
            NetworkState(
                State.ERROR,
                msg
            )
    }
}