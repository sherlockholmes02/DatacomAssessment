package com.exam.datacomassessment.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.datacomassessment.R
import com.exam.datacomassessment.databinding.ActivityMainBinding
import com.exam.datacomassessment.network.ApiInterface
import com.exam.datacomassessment.utils.Coroutines
import com.exam.datacomassessment.utils.NetworkUtil
import com.exam.datacomassessment.utils.RetrofitSingleton
import com.exam.datacomassessment.view.ViewAlbumActivity

class MainActivity : AppCompatActivity(), MainInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toast.makeText(baseContext, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
            return
        }

        initUI()
        initData()
    }

    private fun initUI() {
        albumAdapter = AlbumAdapter().apply {
            setOnItemClickListener {
                if (!NetworkUtil.isNetworkAvailable(baseContext)) {
                    Toast.makeText(baseContext, getString(R.string.no_internet), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemClickListener
                }

                val intent = Intent(this@MainActivity, ViewAlbumActivity::class.java)
                intent.putExtra(getString(R.string.albumId), it)
                startActivity(intent)
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
        viewModel.getUsers()
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