package com.logan.lowesweather_loganmetzger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logan.lowesweather_loganmetzger.databinding.HourlyItemBinding
import com.logan.lowesweather_loganmetzger.models.WeatherDTO
import com.logan.lowesweather_loganmetzger.models.HourlyResponseDTO

class HourlyAdapter(
    private var hourlyWeather: List<HourlyResponseDTO>,
    private val onHourClicked: (weather: HourlyResponseDTO) -> Unit
) : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(private val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(
            hourlyWeather: HourlyResponseDTO
        ) = with(binding) {
            this.hourlyTemp.text = hourlyWeather.weather[0].main
            this.hourlyConditionsTv.text = "Temp: ${hourlyWeather.main.temp.toInt()}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HourlyItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).let {
        HourlyViewHolder(it).apply {
            itemView.setOnClickListener { onHourClicked.invoke(hourlyWeather[adapterPosition]) }
        }
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.loadData(hourlyWeather[position])
    }

    override fun getItemCount(): Int {
        return hourlyWeather.size
    }
}