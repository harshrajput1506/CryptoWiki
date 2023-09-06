package `in`.hypernation.cryptowiki.domain.models

data class CoinList(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
