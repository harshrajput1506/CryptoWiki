package `in`.hypernation.cryptowiki.domain.models

data class CoinList(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,


) {
    fun searchCombinations(query : String) : Boolean{
        val matchingCombinations = listOf(
            id,
            name,
            "$name $symbol",
            "$id $name $symbol",
            symbol,
            "$id$name",
            "$id$symbol"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
