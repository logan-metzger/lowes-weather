package com.logan.lowesweather_loganmetzger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.logan.lowesweather_loganmetzger.R
import com.logan.lowesweather_loganmetzger.adapters.HourlyAdapter
import com.logan.lowesweather_loganmetzger.databinding.WeatherFragmentBinding
import com.logan.lowesweather_loganmetzger.models.HourlyResponseDTO

class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by activityViewModels()

    private var _binding: WeatherFragmentBinding? = null
    private val binding: WeatherFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.title = viewModel.cityName


        with(binding) {
            viewModel.weather.observe(viewLifecycleOwner, {
                hourlyRv.apply {
                    addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                    adapter = HourlyAdapter(it.list, this@WeatherFragment::onWeatherItemSelected)
                }
            })
        }
    }

    private fun onWeatherItemSelected(hourlyWeather: HourlyResponseDTO) {
        viewModel.setUserSelectedWeatherItem(hourlyWeather)
        findNavController().navigate(R.id.detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}