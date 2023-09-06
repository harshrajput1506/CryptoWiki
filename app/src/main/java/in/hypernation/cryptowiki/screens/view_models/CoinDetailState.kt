package `in`.hypernation.cryptowiki.screens.view_models

import `in`.hypernation.cryptowiki.domain.models.Coin

data class CoinDetailState(
    val coin : Coin? = null,
    val isLoading : Boolean = true,
    val error : String = ""
)
