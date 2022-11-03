package com.exam.datacomassessment.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.exam.datacomassessment.R
import com.exam.datacomassessment.databinding.ActivityViewBinding
import com.exam.datacomassessment.network.ApiInterface
import com.exam.datacomassessment.utils.Coroutines
import com.exam.datacomassessment.utils.RetrofitSingleton


/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class ViewAlbumActivity : AppCompatActivity(), ViewAlbumInterface {

    private lateinit var binding: ActivityViewBinding
    private lateinit var viewModel: ViewAlbumViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view)

        initUI()
        initData()
    }

    private fun initUI() {
        binding.ivClose.setOnClickListener {
            finish()
        }
    }

    private fun initData() {
        val apiInterface = RetrofitSingleton.get<ApiInterface>()
        val viewAlbumRepository = ViewAlbumRepository(apiInterface)
        val factory = ViewAlbumViewModelFactory(viewAlbumRepository)
        viewModel = ViewModelProvider(this, factory)[ViewAlbumViewModel::class.java]
        viewModel.viewAlbumListener = this
        val albumId = intent.getIntExtra(getString(R.string.albumId), 0)
        albumId.let { id ->
            viewModel.getPhoto(id)
            viewModel.photoLiveData.observe(this) { photo ->
                // This is to load place holder image
                val url = GlideUrl(
                    photo.url, LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )
                Glide
                    .with(baseContext)
                    .load(url)
                    .into(binding.ivImage)
            }
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