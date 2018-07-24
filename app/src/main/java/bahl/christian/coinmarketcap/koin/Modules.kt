package bahl.christian.coinmarketcap.koin

import androidx.room.Room
import bahl.christian.coinmarketcap.base.scheduler.ISchedulerProvider
import bahl.christian.coinmarketcap.base.scheduler.ScreenSchedulerProvider
import bahl.christian.coinmarketcap.data.source.local.CryptocurrencyDatabase
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApi
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApiClient
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
  single { ScreenSchedulerProvider() as ISchedulerProvider }
  single { OkHttpClient.Builder().build() }
  single { Moshi.Builder().build() }

  single {
    Retrofit.Builder()
      .client(get())
      .baseUrl(CoinMarketApiClient.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(get()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }

  single { get<Retrofit>().create(CoinMarketApi::class.java) }
}

val databaseModule = module {
  single {
    Room.databaseBuilder(androidContext(), CryptocurrencyDatabase::class.java, "cryptocurrency-db").build()
  }
  single { get<CryptocurrencyDatabase>().cryptocurrencyDao() }
}