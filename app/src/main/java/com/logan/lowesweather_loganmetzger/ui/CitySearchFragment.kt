package com.logan.lowesweather_loganmetzger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.logan.lowesweather_loganmetzger.R
import com.logan.lowesweather_loganmetzger.databinding.CitySearchFragmentBinding

class CitySearchFragment : Fragment() {
    private var _binding: CitySearchFragmentBinding? = null
    private val binding: CitySearchFragmentBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CitySearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        with(binding) {
            citySearchButton.setOnClickListener {
                val cityName = binding.cityEt.text.toString()
                viewModel.getWeather(cityName)
            }

            viewModel.shouldNavigate.observe(viewLifecycleOwner, {
                if (it == true) {
                    NavHostFragment.findNavController(this@CitySearchFragment)
                        .navigate(R.id.weatherFragment)
                    viewModel.setShouldNavigate(false)
                }
            })

            viewModel.errorMsg.observe(viewLifecycleOwner, {
                cityInput.error = it
            })

            viewModel.progressIsVisible.observe(viewLifecycleOwner, {
                progressBar.isVisible = it
                citySearchButton.isEnabled = !it
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}