package com.logan.lowesweather_loganmetzger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.logan.lowesweather_loganmetzger.R
import com.logan.lowesweather_loganmetzger.databinding.CitySearchFragmentBinding
import com.logan.lowesweather_loganmetzger.utils.Resource

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

        binding.citySearchButton.setOnClickListener {
            val cityName = binding.cityEt.text.toString()
            viewModel.getWeather(cityName)

            viewModel.weather.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        binding.cityInput.error = response.msg
                    }
                    is Resource.Success -> {
                        NavHostFragment.findNavController(this).navigate(R.id.weatherFragment)
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}