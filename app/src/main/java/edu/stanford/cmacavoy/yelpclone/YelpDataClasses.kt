package edu.stanford.cmacavoy.yelpclone

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("total") val total : Int,
    @SerializedName("businesses") val restaurants : List<YelpRestaurant>
)

data class YelpRestaurant (
    @SerializedName("name") val name : String,
    @SerializedName("rating") val rating : Double,
    @SerializedName("price") val price : String,
    @SerializedName("review_count") val numReviews : Int,
    @SerializedName("distance") val distanceInMeters : Double,
    @SerializedName("image_url") val imageURL : String,
    @SerializedName("categories") val categories : List<YelpCategory>,
    @SerializedName("location") val location : YelpLocation
) {
    fun displayDistance() : String {
        val milesPerMeter = .000621371
        val distanceInMiles = "%.2f".format(distanceInMeters*milesPerMeter)
        return "$distanceInMiles mi"
    }
}

data class YelpCategory(
    @SerializedName("title") val title : String
)

data class YelpLocation(
    @SerializedName("address1") val address : String,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("zip_code") val zipcode : String
)