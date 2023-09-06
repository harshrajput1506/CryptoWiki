package `in`.hypernation.cryptowiki.screens.view_models

import `in`.hypernation.cryptowiki.domain.models.CoinList

data class CoinListState(
    var data : List<CoinList> = emptyList(),
    var error : String = "",
    var isLoading : Boolean = true
)
