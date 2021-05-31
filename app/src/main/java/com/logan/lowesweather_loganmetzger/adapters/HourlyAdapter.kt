package com.logan.lowesweather_loganmetzger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logan.lowesweather_loganmetzger.databinding.HourlyItemBinding
import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO
import com.logan.lowesweather_loganmetzger.utils.Resource

class HourlyAdapter(
    private val onHourClicked: (weather: WeatherResponseDTO) -> Unit,
    private var hourlyWeather: List<WeatherResponseDTO>
) : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(private val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(
            hourlyWeather: WeatherResponseDTO
        ) = with(binding) {
            this.hourlyTemp.text = hourlyWeather.main.temp.toString()
            this.hourlyConditionsTv.text = hourlyWeather.weather[0].main
        }
    }

    fun changeLocations(newWeather: Resource<WeatherResponseDTO>) {
        hourlyWeather = newWeather
        notifyDataSetChanged()
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

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) = with(holder) {
        holder.loadData(hourlyWeather[position])
    }

    override fun getItemCount(): Int {
        return hourlyWeather.size
    }
}