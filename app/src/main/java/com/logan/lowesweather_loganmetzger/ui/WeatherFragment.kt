package com.logan.lowesweather_loganmetzger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.logan.lowesweather_loganmetzger.adapters.HourlyAdapter
import com.logan.lowesweather_loganmetzger.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {
    private var _binding: WeatherFragmentBinding? = null
    private val binding: WeatherFragmentBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hourlyRv.apply {
            adapter = hourlyAdapter
        }

        viewModel.weather.observe(viewLifecycleOwner, Observer {
        })
    }

    fun onHourClick() {

    }
}