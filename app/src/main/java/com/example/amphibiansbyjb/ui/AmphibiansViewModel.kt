package com.example.amphibiansbyjb.ui

import androidx.lifecycle.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansbyjb.AmphibiansApplication
import com.example.amphibiansbyjb.data.AmphibiansRepository

import com.example.amphibiansbyjb.model.AmphibiansModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// limited number of implementations
interface AmphibiansUiState {
    // : AmphibiansUiState means that it's one of the possible states of this interface
    data class Success(val amphibians: List<AmphibiansModel>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository)  : ViewModel() {
    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            uiState = AmphibiansUiState.Loading
            uiState = try {
                val resultList = amphibiansRepository.getAmphibians()
                AmphibiansUiState.Success(
                    resultList
                )
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication) // access the instance of Application class
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
