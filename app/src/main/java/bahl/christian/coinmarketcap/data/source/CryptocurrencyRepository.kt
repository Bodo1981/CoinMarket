package bahl.christian.coinmarketcap.data.source

import android.util.Log
import bahl.christian.coinmarketcap.data.Cryptocurrency
import bahl.christian.coinmarketcap.data.source.local.CryptocurrencyDao
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApi
import io.reactivex.Observable

class CryptocurrencyRepository(val coinMarketApi: CoinMarketApi,
                               val cryptocurrencyDao: CryptocurrencyDao) {

    fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
        val observableFromDb = getCryptocurrenciesFromDb()
        val observableFromApi = getCryptocurrenciesFromApi()

        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    private fun getCryptocurrenciesFromDb() = coinMarketApi.listings()
            .map { it.cryptocurrencies }
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY API * ", it.toString())
                cryptocurrencyDao.insert(it)
            }

    private fun getCryptocurrenciesFromApi() = cryptocurrencyDao.getAll()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
}