package com.ericwall.bookit.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ericwall.bookit.databinding.FragmentRoomListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomListFragment @Inject constructor() : Fragment() {

    val roomListViewModel: RoomListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRoomListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = roomListViewModel

        return binding.root
    }
}