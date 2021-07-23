package com.ericwall.bookit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ericwall.bookit.R
import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.view.RoomListViewModel
import java.util.ArrayList

class LocationAdapter(val viewModel: RoomListViewModel) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    private var locations: List<Location> = ArrayList<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLocation: Location = locations[position]

        holder.locationTitle.text = currentLocation.name
        holder.spotsAvailable.text = "Spots available: ${currentLocation.spots}"
        holder.locationImage.load(currentLocation.imageUrl)
        holder.bookButton.setOnClickListener {
            viewModel.reserveLocation(currentLocation.name)
        }
        holder.bookButton.isEnabled = currentLocation.spots > 0
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun setLocations(locations: List<Location>) {
        this.locations = locations
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val locationTitle: TextView
        internal val spotsAvailable: TextView
        internal val locationImage: ImageView
        internal val bookButton: Button

        init {
            locationTitle = itemView.findViewById(R.id.txt_location_name)
            spotsAvailable = itemView.findViewById(R.id.txt_location_spots_available)
            locationImage = itemView.findViewById(R.id.img_location)
            bookButton = itemView.findViewById(R.id.btn_book_location)
        }
    }

    fun ImageView.load(imageAddress: String) {
        Glide.with(this)
            .load(imageAddress)
            .transform(CenterCrop(), RoundedCorners(10))
            .into(this)
    }
}