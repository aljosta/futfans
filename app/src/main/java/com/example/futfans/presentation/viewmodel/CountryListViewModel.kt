package com.example.futfans.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futfans.domain.mapper.CountryMapper
import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.usecase.FilterCountriesByNameUseCase
import com.example.futfans.domain.usecase.UseCase
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.presentation.model.State
import com.example.futfans.value
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountriesUseCase: UseCase<Any, Flow<List<CountryDto>>>,
    private val filterCountriesByNameUseCase: UseCase<FilterCountriesByNameUseCase.Params, List<CountryDto>>
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Empty)
    val uiState: StateFlow<State>
        get() = _uiState

    private val _countriesData = MutableLiveData<List<CountryModel>>()
    val countriesData: LiveData<List<CountryModel>>
        get() = _countriesData

    private val _searchedCountriesData = MutableLiveData<List<CountryModel>>()
    val searchedCountriesData: LiveData<List<CountryModel>>
        get() = _searchedCountriesData

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var searchJob: Job? = null

    init {
        getCountries()
    }

    private fun getCountries() {
        _loadingState.postValue(true)
        _uiState.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (countriesData.value.isNullOrEmpty()) {
                    getCountriesUseCase.execute().collect {
                        _countriesData.postValue(
                            CountryMapper.transformCollectionDtoToModel(it)
                        )
                        _uiState.value = State.Success(CountryMapper.transformCollectionDtoToModel(it))
                    }
                } else {
                    _countriesData.postValue(countriesData.value)
                    _uiState.value = State.Success(countriesData.value.value())
                }
            } catch (exception: Exception) {
                _errorMessage.postValue(exception.message)
                _uiState.value = State.Error(exception)
            } finally {
                _loadingState.postValue(false)
            }
        }
    }

    fun searchCountry(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                getCountries()
            } else {
                _loadingState.postValue(true)
                try {
                    _searchedCountriesData.postValue(
                        CountryMapper.transformCollectionDtoToModel(
                            filterCountriesByNameUseCase.execute(
                                FilterCountriesByNameUseCase.Params(query)
                            )
                        )
                    )
                } catch (exception: Exception) {
                    _errorMessage.postValue(exception.message)
                } finally {
                    _loadingState.postValue(false)
                }
            }
        }
    }
}
