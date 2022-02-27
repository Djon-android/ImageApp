package com.example.imageapp.screens

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.imageapp.R
import com.example.imageapp.adapters.ImageAdapter

class ImageListActivity : AppCompatActivity() {

    private lateinit var recyclerViewImageList: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        val isTablet = (displayMetrics.widthPixels / displayMetrics.density).toInt() > 600
        var columns = resources.getInteger(R.integer.gallery_columns)
        if (isTablet) {
            columns = 3
        }

        viewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        swipeRefresh = findViewById(R.id.swipeRefresh)
        recyclerViewImageList = findViewById(R.id.recyclerViewListImage)
        adapter = ImageAdapter(width, columns)
        recyclerViewImageList.layoutManager = GridLayoutManager(this, columns)
        recyclerViewImageList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        swipeRefresh.setOnRefreshListener {
            run {
                viewModel.loadData()
                swipeRefresh.isRefreshing = false
            }
        }
        adapter.onClickImageListener = object : ImageAdapter.OnClickImageListener {
            override fun onClickImage(urlImage: String) {
                var intent = ImageOneActivity.newIntent(this@ImageListActivity, urlImage)
                startActivity(intent)
            }

        }
        viewModel.liveDataImage.observe(this, Observer {
            adapter.listImage = it
        })
    }
}