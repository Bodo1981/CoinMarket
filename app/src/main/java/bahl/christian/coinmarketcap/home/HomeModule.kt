package bahl.christian.coinmarketcap.home

import bahl.christian.coinmarketcap.data.source.CryptocurrencyRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val homeModule: Module = applicationContext {
    context("home") {
        bean { CryptocurrencyRepository(get(), get()) }
        bean { HomeViewModel(get()) }
        bean { HomeViewModel2() }
    }
}