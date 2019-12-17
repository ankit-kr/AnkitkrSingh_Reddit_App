package com.ankit.ankitkrsingh_reddit.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.databinding.HomeFragmentBinding
import com.ankit.ankitkrsingh_reddit.domain.LIMIT_POSTS_LIST
import com.ankit.ankitkrsingh_reddit.domain.model.*
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseFragment
import com.ankit.ankitkrsingh_reddit.presentation.common.PaginationScrollListener
import com.ankit.ankitkrsingh_reddit.presentation.home.adapter.PopularItemAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var popularItemList : ObservableArrayList<PopularItem>

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var adapter: PopularItemAdapter

    private val viewModel: SharedHomeViewModel by lazy {
       activity!!.run {
           ViewModelProviders.of(this,viewModelFactory)[SharedHomeViewModel::class.java]
       }
    }

    private lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        setupNavigationObserver()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }
    private fun initData() {
        viewModel.initDataList(popularItemList)
        if(popularItemList.isEmpty())
            viewModel.onRefresh()
    }

    private fun setupNavigationObserver() {
        viewModel.routeEvent.observe(viewLifecycleOwner, Observer {
            val content =it.getContentIfNotHandled()
            when(content?.first){
                SharedHomeViewModel.Route.POPULAR_DETAIL ->{
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                    findNavController().navigate(action)
                }
                SharedHomeViewModel.Route.MENU_POPUP ->{
                    showPopupMenu()
                }
                else -> { }
            }
        })
    }

    private fun showPopupMenu() {
        val popup = PopupMenu(context, binding.flFilterButton)
        popup.menuInflater.inflate(R.menu.home_menu, popup.menu)
        popup.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.actionRising ->{
                    viewModel.setFilter(SharedHomeViewModel.Filter.RISING)
                    viewModel.onRefresh()
                }
                else ->{
                    viewModel.setFilter(SharedHomeViewModel.Filter.HOT)
                    viewModel.onRefresh()
                }
            }
            true
        }
        popup.show()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvPopular.layoutManager = layoutManager
        binding.rvPopular.itemAnimator = DefaultItemAnimator()
        binding.rvPopular.addItemDecoration(DividerItemDecoration(context ,LinearLayoutManager.VERTICAL))
        adapter = PopularItemAdapter(items = popularItemList,requestManager = requestManager)
        binding.rvPopular.adapter = adapter
        adapter.onItemClickListener = { viewModel.onPopularItemClick(it as PopularItem)}

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.onRefresh()
        }
        binding.rvPopular.addOnScrollListener(OnScrollListener(layoutManager))
    }

    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = viewModel.isPostsLoading
        override fun loadMoreItems() = loadNextPage()
        override fun getTotalPageCount() = LIMIT_POSTS_LIST
        override fun isLastPage() = viewModel.isLastPostsPage
    }

    private fun loadNextPage() {
        adapter.addLoadingViewFooter()
        viewModel.updatePopularPostsList()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    private val stateObserver = Observer<PopularListState> { state ->
        state?.let {
            viewModel.isLastPostsPage = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    viewModel.isPostsLoading = false
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is LoadingState -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                    viewModel.isPostsLoading = true
                }
                is PaginatingState -> {
                    viewModel.isPostsLoading = true
                }
                is ErrorState -> {
                    viewModel.isPostsLoading = false
                    binding.swipeRefreshLayout.isRefreshing = false
                    adapter.removeLoadingViewFooter()
                }
            }
        }
    }
}
