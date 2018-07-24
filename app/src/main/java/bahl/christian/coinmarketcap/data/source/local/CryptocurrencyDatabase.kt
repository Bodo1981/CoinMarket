package bahl.christian.coinmarketcap.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import bahl.christian.coinmarketcap.data.Cryptocurrency

@Database(entities = [Cryptocurrency::class], version = 1)
abstract class CryptocurrencyDatabase : RoomDatabase() {

    abstract fun cryptocurrencyDao(): CryptocurrencyDao

}