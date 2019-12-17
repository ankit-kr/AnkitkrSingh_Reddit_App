package com.ankit.ankitkrsingh_reddit.presentation.home.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.databinding.DetailFragmentBinding
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseFragment
import com.ankit.ankitkrsingh_reddit.presentation.common.Constants
import com.ankit.ankitkrsingh_reddit.presentation.home.SharedHomeViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var commentItemList: ObservableArrayList<Any>

    private val viewModel: SharedHomeViewModel by lazy {
        activity!!.run {
            ViewModelProviders.of(this)[SharedHomeViewModel::class.java]
        }
    }

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView()
        setupNavigationObserver()
    }

    private fun initData() {
        viewModel.initCommentsDataList(commentItemList)
        viewModel.getCommentList()
    }

    private fun initView() {
        requestManager.load(viewModel.selectedPopularItem.get()!!.thumbnail).into(binding.ivPopular)
    }

    private fun setupNavigationObserver() {
        viewModel.routeEvent.observe(viewLifecycleOwner, Observer {
            val content = it.getContentIfNotHandled()
            when (content?.first) {
                SharedHomeViewModel.Route.SHARE -> {
                    val data = content.second
                    sharePostLink(data.getString(Constants.POST_URL))
                }
                else -> {
                }
            }
        })
    }

    private fun sharePostLink(postUrl: String?) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, postUrl)
            startActivity(
                Intent.createChooser(
                    shareIntent,
                    getString(R.string.choose_one_text)
                )
            )
        } catch (e: Exception) {
        }

    }

}
