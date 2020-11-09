package edu.stanford.cmacavoy.yelpclone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    inner class  ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(yelpRestaurant: YelpRestaurant) {
            itemView.tvName.text = yelpRestaurant.name
            itemView.ratingBar.rating = yelpRestaurant.rating.toFloat()
            itemView.tvNumReviews.text = "${yelpRestaurant.numReviews} Reviews"
            itemView.tvAddress.text = yelpRestaurant.location.address
            itemView.tvCategory.text = yelpRestaurant.categories[0].title
            itemView.tvDistance.text = yelpRestaurant.displayDistance()
            itemView.tvPrice.text = yelpRestaurant.price
            Glide.with(context).load(yelpRestaurant.imageURL).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.imageView)

            itemView.setOnClickListener{
                val address = "${yelpRestaurant.name}, ${yelpRestaurant.location.address}, ${yelpRestaurant.location.city}, ${yelpRestaurant.location.zipcode}"
                Log.i("Adapter", "Address is: $address")
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${address}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                itemView.getContext().startActivity(mapIntent)

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }
}
