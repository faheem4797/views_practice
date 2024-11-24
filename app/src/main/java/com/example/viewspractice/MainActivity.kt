package com.example.viewspractice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.viewspractice.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumService

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retService = RetrofitInstance.getRetrofit().create(AlbumService::class.java)

        requestWithQueryParameter()
        requestWithPathParameter()

    }

    private fun requestWithQueryParameter(){
        val responseLiveData : LiveData<Response<Album>>  = liveData {
            val response = retService.getSortedAlbums(6)
            emit(response)
        }

        responseLiveData.observe(this, {
            val albumsList = it.body()?.listIterator()
            if(albumsList != null){
                while (albumsList.hasNext()){
                    val albumItem = albumsList.next()
                    val result = " " + "Album Title : ${albumItem.title}" + "\n" +
                            " " + "Album id : ${albumItem.id}" + "\n" +
                            " " + "User id : ${albumItem.userId}" + "\n\n\n"
                    binding.tvAlbums.append(result)
                }
            }
        })
    }

    private fun requestWithPathParameter(){
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getSingleAlbumItem(3)
            emit(response)
        }

        pathResponse.observe(this, {
            val title = it.body()?.title
            Toast.makeText(applicationContext, "album title is $title", Toast.LENGTH_SHORT).show()
        })
    }





}