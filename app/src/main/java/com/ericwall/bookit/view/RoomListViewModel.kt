package com.ericwall.bookit.view

import androidx.lifecycle.*
import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.api.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = _locations

    private val _reservationSuccess = MutableLiveData<Boolean>()
    val reservationSuccess: LiveData<Boolean> get() = _reservationSuccess

    init {
        loadLocations()
    }

    fun loadLocations() {
        viewModelScope.launch {
            locationRepository.getLocations()
                .onStart {
                    // todo add loading dialog
                }
                .catch { exception ->
                    // todo add some kind of error object, or wrap live data with State
                    Timber.e(exception, "Error loading locations try to get from DB $exception")
                    // Probably could handle this better
                    locationRepository.getSavedLocations().collect {
                        _locations.value = it
                    }
                }
                .collect {
                    _locations.value = it
                }
        }
    }

    fun reserveLocation(name: String) {
        viewModelScope.launch {
            locationRepository.reserveLocation(name)
                .onStart {
                    // todo add loading dialog
                }
                .catch { exception ->
                    _reservationSuccess.value = false
                }
                .collect {
                    _reservationSuccess.value = it.success
                }
        }
    }
}