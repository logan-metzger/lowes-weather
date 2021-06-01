package com.logan.lowesweather_loganmetzger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.logan.lowesweather_loganmetzger.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = viewModel.cityName

        with(binding) {
            viewModel.selectedWeatherItm.observe(viewLifecycleOwner, {
                weatherMainTv.text = it.weather[0].main
                weatherDescriptionTv.text = it.weather[0].description
                feelsLikeTv.text = "Feels like: ${it.main?.feelsLike?.toInt()}"
                tempTv.text = it.main.temp.toInt().toString()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}