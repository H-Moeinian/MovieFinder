package com.example.moviefinder

import android.content.Intent
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
    var myList = ArrayList<Search>()
     lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()





        btnSearch.setOnClickListener {
            val movieTitle = this.edtMovieTitle.text.toString()

            disposable.add(
                RetrofitProvider.provideRetrofit().getRelatedMovies(movieTitle)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            myList.clear()
                            myList.addAll(it.search)
                            adapter.notifyDataSetChanged()


                        },
                        {

                        })
            )
        }
    }

    fun showPoster(poster: String) {
        val intent = Intent(this@MainActivity, ShowMoviePosterActivity::class.java)
        intent.putExtra("poster", poster)
        startActivity(intent)
    }

    fun setRecyclerView() {
        adapter = RecyclerViewAdapter(myList) { poster -> showPoster(poster) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
