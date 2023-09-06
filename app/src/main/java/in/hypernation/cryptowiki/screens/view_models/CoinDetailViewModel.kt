package `in`.hypernation.cryptowiki.screens.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.hypernation.cryptowiki.data.repository.CryptoRepository
import `in`.hypernation.cryptowiki.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>("coinId")?.let { id ->
            getCoinById(id)
        }
    }

    private fun getCoinById(id : String){
        viewModelScope.launch {
            when(val result = cryptoRepository.getCoinById(id)){
                is Resource.Success -> {
                    _state.value = CoinDetailState(isLoading = false, coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(isLoading = false, error = result.message ?: "Something went wrong")
                } else -> {
                    _state.value = CoinDetailState(isLoading = true)
                }

            }
        }
    }
}