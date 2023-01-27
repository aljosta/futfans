package com.example.futfans.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futfans.databinding.FragmentTeamBinding
import com.example.futfans.presentation.viewmodel.TeamViewModel
import com.example.futfans.presentation.adapter.ListAdapter
import com.example.futfans.presentation.model.TeamModel
import com.example.futfans.value
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [TeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TeamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var countryName: String = ""

    private lateinit var binding: FragmentTeamBinding
    private val viewModel: TeamViewModel by viewModels()
    private val teamAdapter = ListAdapter(emptyList<TeamModel>()) { onClickItem(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryName = it.getString(COUNTRY_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        lifecycleScope.launch {
            viewModel.getTeams(countryName.value())
        }
    }

    private fun initView() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                viewModel.searchTeam(countryName, text.toString())
            }
        }

        binding.teamListRecyclerView.apply {
            adapter = teamAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers() {
        viewModel.teamsData.observe(viewLifecycleOwner) { teams ->
            setCountryData(teams)
        }

        viewModel.searchedTeamsData.observe(viewLifecycleOwner) { teams ->
            setCountryData(teams)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            binding.teamListRecyclerView.visibility = getVisibilityWhenIsLoading(!isLoading)
            binding.imageProgressBar.visibility = getVisibilityWhenIsLoading(isLoading)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getVisibilityWhenIsLoading(isLoading: Boolean) =
        if (isLoading) View.VISIBLE else View.GONE

    private fun setCountryData(teams: List<TeamModel>) =
        teamAdapter.updateItems(teams)

    private fun onClickItem(teamsId: String? = null) {
        Toast.makeText(context, teamsId, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val COUNTRY_NAME = "countryName"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(countryName: String) =
            TeamFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY_NAME, countryName)
                }
            }
    }
}
