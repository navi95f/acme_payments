package model

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Shift(
    private val dayOfWeek: String,
    private val startHour: String,
    private val finishHour: String
){
     var beginsAt: LocalTime? = null
     var endsAt: LocalTime? = null
     var day: Day? = null


    init {
        parseData()
    }

    private fun parseData() {
        try{
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            beginsAt = LocalTime.parse(startHour, formatter)
            endsAt = LocalTime.parse(finishHour, formatter)
            day = Day.valueOf(dayOfWeek)

        }catch (e: Exception){
            e.printStackTrace()
            println("Error: Exception thrown when parsing hours and day of shift!")
        }
    }

    fun checkShift(): Boolean{
        return day!=null && beginsAt!= null && endsAt!=null
    }

    fun getShiftHours(): Int{
        return try{
            ChronoUnit.HOURS.between(beginsAt, endsAt).toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
    }
}