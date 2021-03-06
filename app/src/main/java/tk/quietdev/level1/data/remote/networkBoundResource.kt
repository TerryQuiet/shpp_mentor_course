package tk.quietdev.level1.data.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import tk.quietdev.level1.utils.Const
import tk.quietdev.level1.utils.Resource

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            delay(Const.NETWORK_DELAY_SIMULATION)
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it).apply {
                    this.message = "REMOTE"
                }
            }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable.message ?: "error while data fetch", it) }
        }
    } else {
        query().map {
            Resource.Success(it).apply {
                this.message = "LOCAL"
            }
        }
    }
    emitAll(flow)
}
