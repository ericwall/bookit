package com.ericwall.bookit.view

import androidx.lifecycle.*
import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.api.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = _locations

    init {
        loadLocations()
    }

    fun loadLocations() {
        viewModelScope.launch {
            locationRepository.getLocations()
                .onStart {
                    println("loadLocations Start")
                }
                .catch { exception ->
                    println("loadLocations Exception $exception")
                }
                .collect {
                    println("loadLocations Collect $it")
                    _locations.value = it
                }
        }
    }
}