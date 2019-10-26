package com.example.moviefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.MovieDatabase.MovieClass
import com.example.moviefinder.MovieDatabase.Search
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()
    private lateinit var movieTitle: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchElement = Search()


        val list = ArrayList<Search>()
        list.add(searchElement)
        recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = RecyclerViewAdapter(list)

        btnSearch.setOnClickListener {
            movieTitle = this.edtMovieTitle.text.toString()

            disposable.add(
                RetrofitProvider.provideRetrofit().getRelatedMovies(movieTitle)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            recyclerView.layoutManager =
                                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                            recyclerView.adapter = RecyclerViewAdapter(it.search)


                        },
                        {

                        })
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
