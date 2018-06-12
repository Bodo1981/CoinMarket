package bahl.christian.coinmarketcap

import android.app.Application
import bahl.christian.coinmarketcap.home.homeModule
import bahl.christian.coinmarketcap.koin.databaseModule
import bahl.christian.coinmarketcap.koin.networkModule
import org.koin.android.ext.android.startKoin

class CoinMarketCapApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, databaseModule, homeModule))
    }
}