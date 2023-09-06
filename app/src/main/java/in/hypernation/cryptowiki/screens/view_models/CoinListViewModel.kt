package `in`.hypernation.cryptowiki.screens.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.hypernation.cryptowiki.data.repository.CryptoRepository
import `in`.hypernation.cryptowiki.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state
    init {
        getCoinList()
    }

    private fun getCoinList(){
        viewModelScope.launch {
            when(val result = cryptoRepository.getCoinList()){
                is Resource.Success -> {
                    _state.value = CoinListState(data = result.data ?: emptyList(), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Something went wrong", isLoading = false)
                }

                else -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }

        }

    }

}