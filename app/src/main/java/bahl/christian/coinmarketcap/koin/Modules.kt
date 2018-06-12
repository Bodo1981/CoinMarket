package bahl.christian.coinmarketcap.koin

import android.arch.persistence.room.Room
import bahl.christian.coinmarketcap.data.source.local.CryptocurrencyDatabase
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApi
import bahl.christian.coinmarketcap.data.source.remote.CoinMarketApiClient
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule: Module = applicationContext {
    bean { OkHttpClient.Builder().build() }
    bean { Moshi.Builder().build() }

    bean {
        Retrofit.Builder()
                .client(get())
                .baseUrl(CoinMarketApiClient.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    bean { get<Retrofit>().create(CoinMarketApi::class.java) }
}

val databaseModule: Module = applicationContext {
    bean { Room.databaseBuilder(androidApplication(), CryptocurrencyDatabase::class.java, "cryptocurrency-db").build() }
    bean { get<CryptocurrencyDatabase>().cryptocurrencyDao() }
}