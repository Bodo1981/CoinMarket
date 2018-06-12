package bahl.christian.coinmarketcap.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import bahl.christian.coinmarketcap.data.Cryptocurrency
import io.reactivex.Single

@Dao
interface CryptocurrencyDao {

    @Query("SELECT * FROM cryptocurrencies")
    fun getAll(): Single<List<Cryptocurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptocurrencies: List<Cryptocurrency>)

}