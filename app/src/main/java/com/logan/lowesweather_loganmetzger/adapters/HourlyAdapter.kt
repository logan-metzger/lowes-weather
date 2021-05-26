package com.logan.lowesweather_loganmetzger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logan.lowesweather_loganmetzger.databinding.HourlyItemBinding
import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO

class HourlyAdapter(
    private val onHourClicked:(position: Int) -> Unit
): RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {
    private var hourlyWeather: List<WeatherResponseDTO> = listOf()

    fun changeLocation(newWeather: List<WeatherResponseDTO>) {
        hourlyWeather = newWeather
        notifyDataSetChanged()
    }

    class HourlyViewHolder(private val binding: HourlyItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun loadData(hourlyWeather: WeatherResponseDTO) = with(binding) {
            this.hourlyTemp.text = hourlyWeather.main.temp.toString()
            this.hourlyConditionsTv.text = hourlyWeather.weather[0].main
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyViewHolder {
        return HourlyViewHolder(
            HourlyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HourlyAdapter.HourlyViewHolder, position: Int) = with(holder) {
        val hourlyWeatherItem = hourlyWeather[position]
        loadData(hourlyWeatherItem)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
       return hourlyWeather.size
    }
}