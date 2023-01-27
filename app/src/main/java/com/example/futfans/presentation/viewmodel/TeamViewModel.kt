package com.example.futfans.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futfans.domain.mapper.TeamMapper
import com.example.futfans.domain.model.TeamDto
import com.example.futfans.domain.usecase.FilterTeamsByNameUseCase
import com.example.futfans.domain.usecase.GetTeamsUseCase
import com.example.futfans.domain.usecase.UseCase
import com.example.futfans.presentation.model.TeamModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamsUseCase: UseCase<GetTeamsUseCase.Params, List<TeamDto>>,
    private val filterTeamByNameUseCase: UseCase<FilterTeamsByNameUseCase.Params, List<TeamDto>>
) : ViewModel() {

    private val _teamsData = MutableLiveData<List<TeamModel>>()
    val teamsData: LiveData<List<TeamModel>>
        get() = _teamsData

    private val _searchedTeamsData = MutableLiveData<List<TeamModel>>()
    val searchedTeamsData: LiveData<List<TeamModel>>
        get() = _searchedTeamsData

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var searchJob: Job? = null

    fun getTeams(countryName: String) {
        _loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (teamsData.value.isNullOrEmpty()) {
                    _teamsData.postValue(
                        TeamMapper.transformCollectionDtoToModel(
                            getTeamsUseCase.execute(
                                GetTeamsUseCase.Params(countryName)
                            )
                        )
                    )
                } else {
                    _teamsData.postValue(teamsData.value)
                }
            } catch (exception: Exception) {
                _errorMessage.postValue(exception.message)
            } finally {
                _loadingState.postValue(false)
            }
        }
    }

    fun searchTeam(countryName: String, query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                getTeams(countryName)
            } else {
                _loadingState.postValue(true)
                try {
                    _searchedTeamsData.postValue(
                        TeamMapper.transformCollectionDtoToModel(
                            filterTeamByNameUseCase.execute(
                                FilterTeamsByNameUseCase.Params(query, countryName)
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
