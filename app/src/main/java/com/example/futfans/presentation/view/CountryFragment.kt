package com.example.futfans.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futfans.R
import com.example.futfans.databinding.FragmentCountryBinding
import com.example.futfans.presentation.adapter.ListAdapter
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.presentation.model.State
import com.example.futfans.presentation.viewmodel.CountryListViewModel
import com.example.futfans.value
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CountryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CountryFragment : Fragment() {
    private val viewModel: CountryListViewModel by viewModels()
    private lateinit var binding: FragmentCountryBinding
    private val countryAdapter = ListAdapter(emptyList<CountryModel>()) { onClickItem(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is State.Success -> {
                            setVisibilityWhenIsLoading(false)
                            setCountryData(it.data)
                        }
                        is State.Error -> {
                            setVisibilityWhenIsLoading(false)
                            showMessage(it.exception.message.toString())
                        }
                        is State.Loading -> {
                            setVisibilityWhenIsLoading(true)
                        }
                        is State.Empty -> {
                        }
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                viewModel.searchCountry(text.toString())
            }
        }

        binding.countryListRecyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers() {
        /* viewModel.countriesData.observe(viewLifecycleOwner) { countries ->
            setCountryData(countries)
        } */

        viewModel.searchedCountriesData.observe(viewLifecycleOwner) { countries ->
            setCountryData(countries)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            binding.countryListRecyclerView.visibility = getVisibilityWhenIsLoading(!isLoading)
            binding.imageProgressBar.visibility = getVisibilityWhenIsLoading(isLoading)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            showMessage(message)
        }
    }

    private fun getVisibilityWhenIsLoading(isLoading: Boolean) =
        if (isLoading) View.VISIBLE else View.GONE

    private fun setVisibilityWhenIsLoading(isLoading: Boolean) =
        if (isLoading) {
            binding.countryListRecyclerView.isVisible = false
            binding.imageProgressBar.isVisible = true
        } else {
            binding.countryListRecyclerView.isVisible = true
            binding.imageProgressBar.isVisible = false
        }

    private fun setCountryData(countries: List<CountryModel>) =
        countryAdapter.updateItems(countries)

    private fun onClickItem(countryId: String? = null) {
        (activity as MainActivity).addFragment(
            R.id.productListFragment,
            TeamFragment.newInstance(countryId.value()),
        )
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
