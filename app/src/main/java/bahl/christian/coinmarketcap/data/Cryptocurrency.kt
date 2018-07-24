package bahl.christian.coinmarketcap.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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