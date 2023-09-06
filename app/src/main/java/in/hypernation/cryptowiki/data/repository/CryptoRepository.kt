package `in`.hypernation.cryptowiki.data.repository

import android.net.http.HttpException
import android.util.Log
import `in`.hypernation.cryptowiki.data.CryptoApi
import `in`.hypernation.cryptowiki.data.dto.toCoin
import `in`.hypernation.cryptowiki.data.dto.toCoinList
import `in`.hypernation.cryptowiki.domain.models.Coin
import `in`.hypernation.cryptowiki.domain.models.CoinList
import `in`.hypernation.cryptowiki.utils.Resource
import java.io.IOException
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val cryptoApi: CryptoApi
) {
    suspend fun getCoinList() : Resource<List<CoinList>>{
        val response = try {
            cryptoApi.getCoins()
        } catch (e : IOException ){
            return Resource.Error<List<CoinList>>(message = "Couldn't reach server. Check your internet connection.")
        } catch (e : Exception){
            return Resource.Error<List<CoinList>>(message = "Couldn't reach server. Check your internet connection.")
        }
        val coinList = response.map { it.toCoinList() }
        return Resource.Success<List<CoinList>>(coinList)
    }

    suspend fun getCoinById(id : String) : Resource<Coin>{
        val response = try {
            cryptoApi.getCoin(id)
        } catch (e : IOException ){
            return Resource.Error<Coin>(message = "Couldn't reach server. Check your internet connection.")
        } catch (e : Exception){
            return Resource.Error<Coin>(message = "Couldn't reach server. Check your internet connection.")
        }
        val coin = response.toCoin()
        return Resource.Success<Coin>(coin)
    }


}