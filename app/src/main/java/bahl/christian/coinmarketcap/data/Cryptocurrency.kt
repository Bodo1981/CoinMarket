package bahl.christian.coinmarketcap.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoCurrencyResponse(@Json(name = "data") val cryptocurrencies: List<Cryptocurrency>)

@Entity(tableName = "cryptocurrencies")
@JsonClass(generateAdapter = true)
data class Cryptocurrency(
        @PrimaryKey val id: Int,
        val name: String,
        val symbol: String,
        @ColumnInfo(name = "website_slug") @Json(name = "website_slug") val websiteSlug: String)