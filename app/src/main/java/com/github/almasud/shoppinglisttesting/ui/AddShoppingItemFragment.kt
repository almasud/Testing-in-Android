package com.github.almasud.shoppinglisttesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.almasud.shoppinglisttesting.R
import com.github.almasud.shoppinglisttesting.databinding.FragmentAddShoppingItemBinding
import com.github.almasud.shoppinglisttesting.other.Status
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_add_shopping_item) {
    private lateinit var mBinding: FragmentAddShoppingItemBinding
    lateinit var viewModel: ShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAddShoppingItemBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        subscribeToObservers()

        mBinding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                mBinding.etShoppingItemName.text.toString(),
                mBinding.etShoppingItemAmount.text.toString(),
                mBinding.etShoppingItemPrice.text.toString()
            )
        }

        mBinding.ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun subscribeToObservers() {
        viewModel.curImageUrl.observe(viewLifecycleOwner) {
            glide.load(it).into(mBinding.ivShoppingImage)
        }
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Snackbar.make(
                            mBinding.root,
                            "Added Shopping Item",
                            Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    }

                    Status.ERROR -> {
                        Snackbar.make(
                            mBinding.root,
                            result.message ?: "An unknown error occcured",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    Status.LOADING -> {
                        /* NO-OP */
                    }
                }
            }
        }
    }
}




















