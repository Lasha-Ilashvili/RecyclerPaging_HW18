package com.example.recyclerpaging_hw18.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.recyclerpaging_hw18.adapter.UserItemAdapter
import com.example.recyclerpaging_hw18.base.BaseFragment
import com.example.recyclerpaging_hw18.databinding.FragmentUserListBinding
import com.example.recyclerpaging_hw18.resources.ResultResponse
import com.example.recyclerpaging_hw18.view_model.UserListViewModel
import kotlinx.coroutines.launch

class UserListFragment : BaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate) {

    private val chatViewModel: UserListViewModel by viewModels()

    private lateinit var adapter: UserItemAdapter

    override fun setRecycler() {
        adapter = UserItemAdapter()
        binding.rvUserList.adapter = adapter
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.userResult.collect { result ->
                    when (result) {
                        is ResultResponse.Success -> {
                            adapter.submitList(result.users.data)
                        }

                        is ResultResponse.Error -> {
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
                        }
                    }
                }
            }
        }
    }
}