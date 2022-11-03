package com.exam.datacomassessment.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.datacomassessment.R
import com.exam.datacomassessment.databinding.ActivityMainBinding
import com.exam.datacomassessment.network.ApiInterface
import com.exam.datacomassessment.utils.Coroutines
import com.exam.datacomassessment.utils.RetrofitSingleton

class MainActivity : AppCompatActivity(), MainInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()
        initData()
    }

    private fun initUI() {
        albumAdapter = AlbumAdapter().apply {
            setOnItemClickListener {

            }
        }

        binding.rvAlbums.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = albumAdapter
        }
    }

    private fun initData() {
        var apiInterface = RetrofitSingleton.get<ApiInterface>()
        val mainRepository = MainRepository(apiInterface)
        val factory = MainViewModelFactory(mainRepository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.mainListener = this
        viewModel.getAlbums()
        viewModel.albumsLiveData.observe(this) { albums ->
            albumAdapter.submitList(albums)
        }
    }

    override fun showProgressBar() {
        Coroutines.main {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        Coroutines.main {
            binding.progressBar.visibility = View.GONE
        }
    }
}