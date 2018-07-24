package bahl.christian.coinmarketcap.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bahl.christian.coinmarketcap.data.Cryptocurrency
import io.reactivex.Single

@Dao
interface CryptocurrencyDao {

    @Query("SELECT * FROM cryptocurrencies")
    fun getAll(): Single<List<Cryptocurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptocurrencies: List<Cryptocurrency>)

}