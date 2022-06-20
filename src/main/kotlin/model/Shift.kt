package model

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Class that represents a single day of work of the employee
 * @param dayOfWeek -> String indicating the code for the day of the week
 * @param startHour -> String that indicates the 'start' of the working hours
 * @param finishHour -> string that indicates the 'finish' of the working hours
 * */
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

    /**
     * Function that parsers the String params of the class into a 'Day' and 'LocalTime' objects for ease of use
     * */
    private fun parseData() {
        try{
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            beginsAt = LocalTime.parse(startHour, formatter)
            endsAt = LocalTime.parse(finishHour, formatter)
            day = Day.valueOf(dayOfWeek)

        }catch (e: Exception){
            //e.printStackTrace()
            println("Error: Exception thrown when parsing hours and day of shift!")
        }
    }

    /**
     * Function that verifies if the String data was correctly parsed
     * */
    fun checkShift(): Boolean{
        return day!=null && beginsAt!= null && endsAt!=null
    }

    /**
     * @return -> number of hours worked on this single shift
     * */
    fun getShiftHours(): Int{
        return try{
            ChronoUnit.HOURS.between(beginsAt, endsAt).toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
    }
}