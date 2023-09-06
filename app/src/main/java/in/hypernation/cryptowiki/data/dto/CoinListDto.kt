package `in`.hypernation.cryptowiki.data.dto

import `in`.hypernation.cryptowiki.domain.models.CoinList

data class CoinListDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinListDto.toCoinList() : CoinList {
    return CoinList(
        id = id,
        isActive = is_active,
        rank = rank,
        name = name,
        symbol = symbol
    )
}