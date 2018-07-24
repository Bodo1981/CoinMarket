package bahl.christian.coinmarketcap

import android.app.Application
import bahl.christian.coinmarketcap.koin.databaseModule
import bahl.christian.coinmarketcap.koin.networkModule
import bahl.christian.coinmarketcap.screen.ScreenFragment.Companion.screenModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class CoinMarketCapApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }

    startKoin(this, listOf(networkModule, databaseModule, screenModule))
  }

}