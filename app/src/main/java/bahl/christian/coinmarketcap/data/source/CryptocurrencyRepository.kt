package bahl.christian.coinmarketcap.data.source

import bahl.christian.coinmarketcap.data.Cryptocurrency
import bahl.christian.coinmarketcap.data.source.local.CryptocurrencyDao
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApi
import io.reactivex.Observable
import timber.log.Timber

class CryptocurrencyRepository(val coinMarketApi: CoinMarketApi,
  val cryptocurrencyDao: CryptocurrencyDao) {

  fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
    val observableFromDb = getCryptocurrenciesFromDb()
    val observableFromApi = getCryptocurrenciesFromApi()

    return Observable.concatArrayEager(observableFromDb, observableFromApi)
  }

  private fun getCryptocurrenciesFromApi() = coinMarketApi.listings()
    .map { it.cryptocurrencies }
    .toObservable()
    .doOnNext {
      Timber.e("REPOSITORY API * $it")
      cryptocurrencyDao.insert(it)
    }

  private fun getCryptocurrenciesFromDb() = cryptocurrencyDao.getAll()
    .toObservable()
    .doOnNext {
      Timber.e("REPOSITORY DB *** it")
    }
}