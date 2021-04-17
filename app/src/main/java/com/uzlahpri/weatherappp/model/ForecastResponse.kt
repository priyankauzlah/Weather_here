package com.uzlahpri.weatherappp.model

import com.google.gson.annotations.SerializedName
import com.uzlahpri.weatherappp.model.all.Clouds
import com.uzlahpri.weatherappp.model.all.Coord
import com.uzlahpri.weatherappp.model.all.Main
import com.uzlahpri.weatherappp.model.all.Rain
import com.uzlahpri.weatherappp.model.all.Sys
import com.uzlahpri.weatherappp.model.all.WeatherItem
import com.uzlahpri.weatherappp.model.all.Wind

data class ForecastResponse(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: Int? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)


data class ListItem(

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("pop")
	val pop: Double? = null,

	@field:SerializedName("rain")
	val rain: Rain? = null,

	@field:SerializedName("visibility")
	val visibility: Int? = null,

	@field:SerializedName("dt_txt")
	val dtTxt: String? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("main")
	val main: Main? = null,

	@field:SerializedName("clouds")
	val clouds: Clouds? = null,

	@field:SerializedName("sys")
	val sys: Sys? = null,

	@field:SerializedName("wind")
	val wind: Wind? = null
)

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("sunrise")
	val sunrise: Int? = null,

	@field:SerializedName("timezone")
	val timezone: Int? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("population")
	val population: Int? = null
)