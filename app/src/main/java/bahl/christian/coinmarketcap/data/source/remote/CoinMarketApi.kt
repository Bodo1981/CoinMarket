package bahl.christian.coinmarketcap.data.source.remote

import bahl.christian.coinmarketcap.data.CryptoCurrencyResponse
import bahl.christian.coinmarketcap.data.Cryptocurrency
import io.reactivex.Single
import retrofit2.http.GET

interface CoinMarketApi {

    @GET("listings/")
    fun listings(): Single<CryptoCurrencyResponse>

}