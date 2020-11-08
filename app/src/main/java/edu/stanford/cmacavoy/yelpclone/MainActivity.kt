package edu.stanford.cmacavoy.yelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val YELP_API_KEY = "sI7Up_0uclM6gMBod4r03taQMp-rSbyR2ifkbqLtg6anNXRQ2ySW-HCNSVMYxxbxI4Ynt9CvSey6dFGO8q1szJJaM_qiMue9jyJgUl-GV0d-WrguRIP-47LwSXqoX3Yx"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants( "Bearer $YELP_API_KEY","Avocado Toast", "New York")
            .enqueue(object : Callback<YelpSearchResult> {
                override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                    Log.i(TAG, "failure $t")
                }

                override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                    Log.i(TAG, "onResponse $response")
                }

            })
    }
}