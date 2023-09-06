package `in`.hypernation.cryptowiki.domain.models

import `in`.hypernation.cryptowiki.data.dto.Tag
import `in`.hypernation.cryptowiki.data.dto.Team

data class Coin(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val logo: String,
    val name: String,
    val openSource: Boolean,
    val rank: Int,
    val symbol: String,
    val tags: List<Tag>?,
    val team: List<Team>,
)
