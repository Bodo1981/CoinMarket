package bahl.christian.coinmarketcap.data.source.remote

import bahl.christian.coinmarketcap.data.CryptoCurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinMarketApi {

  @GET("listings/")
  fun listings(): Single<CryptoCurrencyResponse>

  @GET("ticker/{id}")
  fun ticker(@Path("id") id: Int, @Query("convert") convertTo: String = "EUR")

}