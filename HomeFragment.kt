package com.example.kripto.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kripto.base.BaseFragment
import com.example.kripto.databinding.FragmentHomeBinding
import com.example.kripto.model.crypto.Data
import com.example.kripto.utils.Constants.API_KEY
import com.example.kripto.utils.Constants.LIMIT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {


    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateFinished() {
        viewModel.getData(API_KEY, LIMIT)
    }

    override fun initializeListeners() {
        // Burada gerekirse listener'larınızı başlatın
    }

    override fun observeEvents() {
        with(viewModel) {
            cryptoResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                 it.data?.let { it1->setRecycler(it1) }
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleView(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }
    }
    private fun setRecycler(data: List<Data?>) {
        val mAdapter = RecyclerAdapter(object : ItemClicklistener {
            override fun onItemClick(coin: Data) {
                //TODO Diğer ekrana yolla
            }
        })
        binding.rcyHome.adapter = mAdapter
        mAdapter.setList(data as List<Data>)
    }

    private fun handleView(isLoading: Boolean) {
        binding.rcyHome.isVisible = !isLoading
        binding.pbHome.isVisible = isLoading
    }
}
