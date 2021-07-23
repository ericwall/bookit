package com.ericwall.bookit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ericwall.bookit.databinding.FragmentRoomListBinding
import com.ericwall.bookit.view.adapter.LocationAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomListFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentRoomListBinding
    private val roomListViewModel: RoomListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = roomListViewModel

        binding.rvLocations.layoutManager = GridLayoutManager(this.context, 4)
        binding.rvLocations.adapter = LocationAdapter(roomListViewModel)

        observeLocations()
        observeReservation()

        return binding.root
    }

    fun observeLocations() {
        roomListViewModel.locations.observe(viewLifecycleOwner,
            { locations ->
                locations?.let { locationList ->
                    (binding.rvLocations.adapter as? LocationAdapter)?.setLocations(locationList)
                }
            })
    }

    fun observeReservation() {
        roomListViewModel.reservationSuccess.observe(viewLifecycleOwner, { success ->

            val message = if (success) {
                "Location Booked"
            } else {
                "Could not book Location, check connection"
            }

            this.view?.let {
                Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

}