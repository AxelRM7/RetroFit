package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.remote.PokemonEntry
import com.example.retrofit.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("7")

        retrofit.enqueue(object: retrofit2.Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                val responseBody = response.body()
                if(responseBody != null){
                    Log.d("RetrofitResponse","res: ${response.body()}")
                    Log.d("RetrofitResponse","Type: ${responseBody.types[0].type.name}")
                    for (stat in responseBody.stats){
                        Log.d("RetrofitResponse","${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                    Log.d("RetrofitResponse","URLImage: ${responseBody.sprites.front_default}")
                }

            }

            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.d("RetrofitResponse","error: ${t.message}")
            }
        })
    }
}